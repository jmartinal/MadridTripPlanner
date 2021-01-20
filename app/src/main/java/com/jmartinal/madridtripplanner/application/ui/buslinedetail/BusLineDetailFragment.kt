package com.jmartinal.madridtripplanner.application.ui.buslinedetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.jmartinal.madridtripplanner.application.common.EventObserver
import com.jmartinal.madridtripplanner.databinding.FragmentBusLineDetailBinding
import org.koin.androidx.scope.lifecycleScope
import org.koin.androidx.viewmodel.scope.viewModel
import org.koin.core.parameter.parametersOf

class BusLineDetailFragment : Fragment() {

    private lateinit var binding: FragmentBusLineDetailBinding
    private val viewModel: BusLineDetailViewModel by lifecycleScope.viewModel(this) {
        parametersOf(args.lineLabel)
    }
    private val args: BusLineDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBusLineDetailBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initUI()
        initVM()
    }

    private fun initUI() {
        binding.tvLine.text = "Bus Line: ${args.lineLabel}"
    }

    private fun initVM() {
        with(viewModel) {
            state.observe(viewLifecycleOwner, Observer(::updateUI))
            action.observe(viewLifecycleOwner, EventObserver(::performAction))
            navigation.observe(viewLifecycleOwner, EventObserver(::navigateTo))
        }
    }

    private fun updateUI(state: BusLineDetailUiModel) {

    }

    private fun performAction(action: BusLineDetailAction) {

    }

    private fun navigateTo(destination: BusLineDetailDestination) {

    }

}