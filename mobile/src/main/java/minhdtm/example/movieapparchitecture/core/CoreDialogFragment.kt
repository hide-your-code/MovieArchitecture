package minhdtm.example.movieapparchitecture.core

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatDialogFragment
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import minhdtm.example.movieapparchitecture.BR
import minhdtm.example.movieapparchitecture.extension.autoClear
import minhdtm.example.movieapparchitecture.extension.executeAfter

abstract class CoreDialogFragment<Binding : ViewDataBinding, VM : ViewModel> : AppCompatDialogFragment() {

    protected var binding by autoClear<Binding>()

    protected abstract val viewModel: VM

    @get:LayoutRes
    protected abstract val layoutId: Int

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog =
        MaterialAlertDialogBuilder(requireContext()).create()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.executeAfter {
            setVariable(BR.viewModel, savedInstanceState)
            lifecycleOwner = viewLifecycleOwner
        }

        if (showsDialog) {
            (requireDialog() as? AlertDialog)?.setView(binding.root)
        }
    }
}
