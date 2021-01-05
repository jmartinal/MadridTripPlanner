package com.jmartinal.madridtripplanner.application.ui.buslinedetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.jmartinal.madridtripplanner.databinding.FragmentBusLineDetailBinding
import org.koin.androidx.scope.lifecycleScope

class BusLineDetailFragment : Fragment() {

    private lateinit var binding: FragmentBusLineDetailBinding
    private val viewModel: BusLineDetailViewModel by lifecycleScope.inject()
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
        binding.tvLine.text = "Bus Line: ${args.line}"
    }

    private fun initVM() {

    }

}