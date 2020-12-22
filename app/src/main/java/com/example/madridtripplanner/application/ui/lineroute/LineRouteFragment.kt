package com.example.madridtripplanner.application.ui.lineroute

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.madridtripplanner.databinding.FragmentLineRouteBinding
import org.koin.androidx.scope.lifecycleScope
import org.koin.androidx.viewmodel.scope.viewModel

class LineRouteFragment : Fragment() {

    private lateinit var binding: FragmentLineRouteBinding
    private val viewModel: LineRouteViewModel by lifecycleScope.viewModel(this)

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        binding = FragmentLineRouteBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
        initVM()
    }

    private fun initUI() {
        // TODO: Initialize UI
    }

    private fun initVM() {
        with(this.viewModel) {
            // TODO: Start observing LiveData
        }
    }

}