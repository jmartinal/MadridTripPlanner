package com.example.madridtripplanner.application.ui.about

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.madridtripplanner.BuildConfig
import com.example.madridtripplanner.R
import com.example.madridtripplanner.databinding.FragmentAboutBinding
import org.koin.androidx.scope.lifecycleScope
import org.koin.androidx.viewmodel.scope.viewModel

class AboutFragment : Fragment() {

    private lateinit var binding: FragmentAboutBinding
    private val viewModel: AboutViewModel by lifecycleScope.viewModel(this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAboutBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initVM()
    }

    private fun initVM() {
        with(viewModel) {
            state.observe(viewLifecycleOwner, Observer(::updateUi))
        }
    }

    private fun updateUi(uiModel: AboutUiModel) {
        when (uiModel) {
            is AboutUiModel.Default -> showInfo(uiModel.apiVersion)
            is AboutUiModel.Loading -> showLoading(uiModel.messageResId)
        }
    }

    private fun showLoading(messageResID: Int) {
        with(binding) {
            tvLoadingMessage.text = getString(messageResID)
            clLoading.visibility = View.VISIBLE
        }
    }

    private fun hideLoading() {
        with(binding) {
            clLoading.visibility = View.GONE
            clInfo.visibility = View.VISIBLE
        }
    }

    private fun showInfo(apiVersion: String) {
        with(binding) {
            tvAppVersion.text = getString(R.string.about_version, BuildConfig.VERSION_NAME)
            tvEmtGreetings.text = getString(R.string.about_emt_mention, apiVersion)
        }
        hideLoading()
    }

}