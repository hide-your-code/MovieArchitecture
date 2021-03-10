package minhdtm.example.movieapparchitecture.ui

import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowInsets
import android.view.WindowInsets.Type
import androidx.activity.viewModels
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.core.view.updatePadding
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import minhdtm.example.movieapparchitecture.R
import minhdtm.example.movieapparchitecture.databinding.ActivityMainBinding
import minhdtm.example.movieapparchitecture.databinding.NavigationHeaderBinding
import minhdtm.example.movieapparchitecture.extension.doOnApplyWindowInserts
import minhdtm.example.movieapparchitecture.extension.systemInsetsBottom
import minhdtm.example.movieapparchitecture.extension.systemInsetsLeft
import minhdtm.example.movieapparchitecture.extension.systemInsetsRight
import minhdtm.example.movieapparchitecture.extension.systemInsetsTop
import minhdtm.example.movieapparchitecture.ui.base.BaseActivity
import minhdtm.example.movieapparchitecture.ui.base.NavigationHost
import minhdtm.example.movieapparchitecture.util.Constant.ScreenName.ACTIVITY_MAIN
import minhdtm.example.movieapparchitecture.util.HeightTopWindowInsetsListener
import minhdtm.example.movieapparchitecture.util.NoopWindowInsetsListener
import minhdtm.example.movieapparchitecture.util.navigationItemBackground
import minhdtm.example.movieapparchitecture.widget.HashtagCinemaDecoration

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(), NavigationHost {

    override val viewModel: MainViewModel by viewModels()

    override val layoutId: Int = R.layout.activity_main

    override val screenName: String = ACTIVITY_MAIN

    private var currentNavId = NAV_ID_NONE

    private var navController: NavController? = null
    private var navHeaderBinding: NavigationHeaderBinding? = null
    private var navHostFragment: NavHostFragment? = null

    private val drawOnDestinationChangedListener: NavController.OnDestinationChangedListener by lazy {
        NavController.OnDestinationChangedListener { _, destination, _ ->
            currentNavId = destination.id
            val isTopLevelDestination = TOP_LEVEL_DESTINATION.contains(destination.id)
            val lockMode = if (isTopLevelDestination) {
                DrawerLayout.LOCK_MODE_UNLOCKED
            } else {
                DrawerLayout.LOCK_MODE_LOCKED_CLOSED
            }
            binding?.drawer?.setDrawerLockMode(lockMode)
        }
    }

    private val appBarConfiguration: AppBarConfiguration by lazy {
        AppBarConfiguration(TOP_LEVEL_DESTINATION, binding?.drawer)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupDrawer()
        setupContentView()
        setupNavigation()
    }

    @Suppress("DEPRECATION")
    private fun setupDrawer() {
        // Let's consume any
        binding?.drawerContainer?.setOnApplyWindowInsetsListener { v, insets ->

            v.onApplyWindowInsets(insets)

            // Consume any horizontal insets and pad all content in. There's not much we can do with horizontal insets
            v.updatePadding(
                left = insets.systemInsetsLeft(),
                right = insets.systemInsetsRight()
            )

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                val insetDrawer = insets.getInsets(Type.systemBars()).apply {
                    left
                    right
                }

                WindowInsets.Builder()
                    .setInsets(Type.systemBars(), insetDrawer)
                    .build()
            } else {
                insets.replaceSystemWindowInsets(
                    0, insets.systemInsetsTop(),
                    0, insets.systemInsetsBottom()
                )
            }
        }
    }

    @Suppress("DEPRECATION")
    private fun setupContentView() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.setDecorFitsSystemWindows(false)
        } else {
            binding?.flContainer?.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                    View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
        }

        binding?.flContainer?.setOnApplyWindowInsetsListener(NoopWindowInsetsListener)
        binding?.statusBarScrim?.setOnApplyWindowInsetsListener(HeightTopWindowInsetsListener)
    }

    private fun setupNavigation() {
        // Init header navigation drawer
        navHeaderBinding = NavigationHeaderBinding.inflate(layoutInflater).apply {
            lifecycleOwner = this@MainActivity
        }

        // Init navigation controller
        navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment?.navController

        // Set up drawer
        navController?.addOnDestinationChangedListener(drawOnDestinationChangedListener)

        binding?.navigation?.run {
            // Add footer drawer with itemDecoration
            val menu = findViewById<RecyclerView>(R.id.design_navigation_view)
            menu.addItemDecoration(HashtagCinemaDecoration(context))

            // Add header drawer
            navHeaderBinding?.root?.let {
                it.doOnApplyWindowInserts { view, windowInsetsCompat, viewPaddingState ->
                    // Update padding logo header's top to status bar
                    view.updatePadding(top = viewPaddingState.top + windowInsetsCompat.systemWindowInsetTop)
                }

                addHeaderView(it)
            }

            itemBackground = navigationItemBackground(this@MainActivity)

            navController?.let(::setupWithNavController)
        }
    }

    override fun registerToolbarWithNavigation(toolbar: Toolbar) {
        navController?.let {
            toolbar.setupWithNavController(it, appBarConfiguration)
            toolbar.inflateMenu(R.menu.menu_main)
        }
    }

    override fun onBackPressed() {
        if (binding?.drawer?.isDrawerOpen(binding?.navigation!!) == true) {
            binding?.drawer?.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onDestroy() {
        binding?.run {
            drawerContainer.setOnApplyWindowInsetsListener(null)
            flContainer.setOnApplyWindowInsetsListener(null)
            statusBarScrim.setOnApplyWindowInsetsListener(null)
        }
        navController?.removeOnDestinationChangedListener(drawOnDestinationChangedListener)
        navController = null
        navHeaderBinding = null
        navHostFragment = null
        super.onDestroy()
    }

    companion object {
        private const val NAV_ID_NONE = -1

        private val TOP_LEVEL_DESTINATION = setOf(
            R.id.navigation_movie_trending,
            R.id.navigation_information,
            R.id.navigation_setting
        )
    }
}
