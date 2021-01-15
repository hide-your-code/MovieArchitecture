package minhdtm.example.movieapparchitecture.core

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel

abstract class CoreActivity<ViewBinding : ViewDataBinding, VM : ViewModel> : AppCompatActivity() {

    var binding: ViewBinding? = null

    abstract val viewModel: VM

    @get:LayoutRes
    abstract val layoutId: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Binding
        binding = DataBindingUtil.setContentView(this, layoutId)
        binding?.lifecycleOwner = this
    }

    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }
}
