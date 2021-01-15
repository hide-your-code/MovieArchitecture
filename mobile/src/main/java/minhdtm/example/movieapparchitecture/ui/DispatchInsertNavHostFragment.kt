package minhdtm.example.movieapparchitecture.ui

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.core.view.forEach
import androidx.navigation.fragment.NavHostFragment

class DispatchInsertNavHostFragment : NavHostFragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.setOnApplyWindowInsetsListener { v, inserts ->
            (v as? ViewGroup)?.forEach { child ->
                child.dispatchApplyWindowInsets(inserts)
            }
            inserts
        }
    }
}
