package com.jmartinal.madridtripplanner.application.ui.lineinfodetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.jmartinal.madridtripplanner.databinding.FragmentLineInfoDetailBinding
import org.koin.androidx.scope.lifecycleScope

class LineInfoDetailFragment : Fragment() {

    private lateinit var binding: FragmentLineInfoDetailBinding
    private val viewModel: LineInfoDetailViewModel by lifecycleScope.inject()
    private val args: LineInfoDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLineInfoDetailBinding.inflate(layoutInflater, container, false)
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