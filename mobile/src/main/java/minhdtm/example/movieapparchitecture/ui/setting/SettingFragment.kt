package minhdtm.example.movieapparchitecture.ui.setting

import androidx.fragment.app.viewModels
import minhdtm.example.movieapparchitecture.R
import minhdtm.example.movieapparchitecture.databinding.FragmentSettingBinding
import minhdtm.example.movieapparchitecture.ui.base.BaseFragment

class SettingFragment : BaseFragment<FragmentSettingBinding, SettingViewModel>() {

    override val screenName: String = ""

    override val viewModel: SettingViewModel by viewModels()

    override val layoutId: Int = R.layout.fragment_setting
}
