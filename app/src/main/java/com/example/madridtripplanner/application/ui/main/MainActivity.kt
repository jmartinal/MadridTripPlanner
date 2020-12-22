package com.example.madridtripplanner.application.ui.main

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.madridtripplanner.R
import com.example.madridtripplanner.application.ui.common.EventObserver
import com.example.madridtripplanner.application.ui.main.MainUiModel.*
import com.example.madridtripplanner.databinding.ActivityMainBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import org.koin.androidx.scope.lifecycleScope
import org.koin.androidx.viewmodel.scope.viewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var appBarConfiguration: AppBarConfiguration
    private val mainViewModel: MainViewModel by lifecycleScope.viewModel(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initUI()
        initVM()
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    private fun initUI() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        with(binding) {
            setSupportActionBar(appBarMain.toolbar)

            val navController = findNavController(R.id.nav_host_fragment)
            // Passing each menu ID as a set of Ids because each
            // menu should be considered as top level destinations.
            appBarConfiguration = AppBarConfiguration(
                setOf(
                    R.id.nav_new_route,
                    R.id.nav_line_route,
                    R.id.nav_line_schedule,
                    R.id.nav_refresh_data,
                    R.id.nav_about
                ), drawerLayout
            )
            setupActionBarWithNavController(navController, appBarConfiguration)
            navView.setupWithNavController(navController)
        }
    }

    private fun initVM() {
        with(mainViewModel) {
            state.observe(this@MainActivity, Observer(::updateUi))
            action.observe(this@MainActivity, EventObserver(::performAction))
        }
    }

    private fun updateUi(mainUiModel: MainUiModel) {
        when (mainUiModel) {
            is Loading -> showLoading(mainUiModel.messageResID)
            is Default -> hideLoading()
        }
    }

    private fun performAction(action: MainAction) {
        when (action) {
            is MainAction.ShowError -> showError(action.messageResID)
        }
    }

    private fun showLoading(messageResID: Int) {
        with(binding) {
            tvLoadingMessage.text = getString(messageResID)
            appBarMain.root.visibility = View.GONE
            clLoading.visibility = View.VISIBLE
        }
    }

    private fun hideLoading() {
        with(binding) {
            clLoading.visibility = View.GONE
            appBarMain.root.visibility = View.VISIBLE
        }
    }

    private fun showError(messageResID: Int) {
        MaterialAlertDialogBuilder(this)
            .setTitle(getString(R.string.error_title))
            .setMessage(getString(messageResID))
            .setPositiveButton(getString(R.string.button_accept)) { _, _ -> finish() }
            .show()
    }
}