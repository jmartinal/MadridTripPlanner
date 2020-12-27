package com.jmartinal.madridtripplanner.application.ui.newroute

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.jmartinal.madridtripplanner.databinding.FragmentNewRouteBinding
import org.koin.androidx.scope.lifecycleScope
import org.koin.androidx.viewmodel.scope.viewModel

class NewRouteFragment : Fragment() {

    private lateinit var binding: FragmentNewRouteBinding
    private val viewModel: NewRouteViewModel by lifecycleScope.viewModel(this)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNewRouteBinding.inflate(layoutInflater, container, false)
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
        with(viewModel) {
            // TODO: Start observing LiveData
        }
    }

}