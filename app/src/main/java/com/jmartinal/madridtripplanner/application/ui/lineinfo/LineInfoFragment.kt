package com.jmartinal.madridtripplanner.application.ui.lineinfo

import android.content.Context
import android.os.Bundle
import android.text.InputType
import android.view.*
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.jmartinal.madridtripplanner.R
import com.jmartinal.madridtripplanner.application.common.EventObserver
import com.jmartinal.madridtripplanner.databinding.FragmentLineInfoBinding
import com.jmartinal.madridtripplanner.domain.BusLine
import org.koin.androidx.scope.lifecycleScope
import org.koin.androidx.viewmodel.scope.viewModel


class LineInfoFragment : Fragment() {

    private lateinit var binding: FragmentLineInfoBinding
    private val viewModel: LineInfoViewModel by lifecycleScope.viewModel(this)
    private lateinit var adapter: LineInfoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onPause() {
        hideKeyboard()
        super.onPause()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLineInfoBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
        initVM()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_line_info, menu)
        val search = menu.findItem(R.id.action_search)
        (search.actionView as SearchView).apply {
            inputType = InputType.TYPE_TEXT_FLAG_CAP_SENTENCES
            queryHint = getString(R.string.line_info_search_hint)
            imeOptions = EditorInfo.IME_ACTION_SEARCH
            setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String): Boolean {
                    viewModel.onSearchQuerySubmitted(query)
                    clearFocus()
                    return true
                }

                override fun onQueryTextChange(newText: String): Boolean {
                    viewModel.onSearchQueryTextChanged(newText)
                    return true
                }

            })
        }
        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun initUI() {
        with(binding) {
            adapter = LineInfoAdapter(viewModel::onBusLineClicked)
            rvBusLines.adapter = adapter
        }
    }

    private fun initVM() {
        with(this.viewModel) {
            state.observe(viewLifecycleOwner, Observer(::updateUI))
            action.observe(viewLifecycleOwner, EventObserver(::performAction))
            navigation.observe(viewLifecycleOwner, EventObserver(::navigateTo))
        }
    }

    private fun updateUI(uiModel: LineInfoUiModel) {
        when (uiModel) {
            is LineInfoUiModel.Loading -> showLoading(uiModel.messageResID)
            is LineInfoUiModel.ShowBusLines -> showBusLines(uiModel.busLines)
            LineInfoUiModel.ShowEmptyResults -> showNoResults()
        }
    }

    private fun performAction(action: LineInfoAction) {
        when (action) {

        }
    }

    private fun navigateTo(destination: LineInfoDestination) {
        when (destination) {

        }
    }

    private fun showLoading(messageResID: Int) {
        with(binding) {
            tvEmptyList.visibility = View.GONE
            rvBusLines.visibility = View.GONE
            tvLoadingMessage.text = getString(messageResID)
            clLoading.visibility = View.VISIBLE
        }
    }

    private fun hideLoading() {
        with(binding) {
            clLoading.visibility = View.GONE
            tvEmptyList.visibility = View.GONE
            rvBusLines.visibility = View.VISIBLE
        }
    }

    private fun showNoResults() {
        with(binding) {
            tvEmptyList.visibility = View.VISIBLE
            rvBusLines.visibility = View.GONE
            clLoading.visibility = View.GONE
        }
    }

    private fun showBusLines(busLines: List<BusLine>) {
        adapter.submitList(busLines) {
            binding.rvBusLines.scrollToPosition(0)
            hideLoading()
        }
    }

    private fun hideKeyboard() {
        val inputMethodManager =
            requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view?.windowToken, 0)
    }

}