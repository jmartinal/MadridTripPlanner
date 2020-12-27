package com.jmartinal.madridtripplanner.application.ui.refreshdata

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.jmartinal.madridtripplanner.R
import com.jmartinal.madridtripplanner.application.common.EventObserver
import com.jmartinal.madridtripplanner.databinding.FragmentRefreshDataBinding
import org.koin.androidx.scope.lifecycleScope
import org.koin.androidx.viewmodel.scope.viewModel
import java.text.SimpleDateFormat
import java.util.*

class RefreshDataFragment : Fragment() {

    private lateinit var binding: FragmentRefreshDataBinding
    private val viewModel: RefreshDataViewModel by lifecycleScope.viewModel(this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRefreshDataBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
        initVM()
    }

    private fun initUI() {
        with(binding) {
            fabRefreshAppData.setOnClickListener { viewModel.onRefreshAppDataClicked() }
        }
    }

    private fun initVM() {
        with(this.viewModel) {
            state.observe(viewLifecycleOwner, Observer(::updateUI))
            action.observe(viewLifecycleOwner, EventObserver(::performAction))
        }
    }

    private fun updateUI(uiModel: RefreshDataUiModel) {
        when (uiModel) {
            is RefreshDataUiModel.Loading -> showLoading(uiModel.messageResID)
            is RefreshDataUiModel.Default -> showInfo(uiModel.updatedAt)
        }
    }

    private fun performAction(action: RefreshDataAction) {
        when (action) {
            is RefreshDataAction.ShowError -> showError(action.messageResID)
        }
    }

    private fun showLoading(messageResID: Int) {
        with(binding) {
            clInfo.visibility = View.GONE
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

    private fun showInfo(updatedAt: Long) {
        with(binding) {
            val formatter = SimpleDateFormat.getDateTimeInstance(
                SimpleDateFormat.MEDIUM,
                SimpleDateFormat.SHORT
            )
            tvLastUpdateValue.text = formatter.format(Date(updatedAt))
        }
        hideLoading()
    }

    private fun showError(messageResID: Int) {
        hideLoading()
        MaterialAlertDialogBuilder(requireContext())
            .setTitle(getString(R.string.error_title))
            .setMessage(getString(messageResID))
            .setPositiveButton(getString(R.string.common_accept)) { _, _ -> }
            .show()

    }
}