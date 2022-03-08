package com.example.navigationalcomponents

import android.os.Bundle
import android.view.Menu
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.onNavDestinationSelected
import androidx.navigation.ui.setupWithNavController
import com.example.navigationalcomponents.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    private fun initializations() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView_Main) as NavHostFragment
        navController = navHostFragment.navController

        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.fragmentSplash,
                R.id.fragmentHome,
                R.id.fragmentSetting,
                R.id.fragmentNotification
            ),
            binding.drawerLayoutMain
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initializations()
        setMenus()
        callbackToolbar()
        configureToolbar()
    }

    private fun setMenus() {
        setSupportActionBar(binding.toolbarMain)
        binding.toolbarMain.setupWithNavController(navController, appBarConfiguration)
        binding.navigationViewMain.setupWithNavController(navController)
        binding.bottomNavigationViewMain.setupWithNavController(navController)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_toolbar, menu)
        return super.onCreateOptionsMenu(menu)
    }

    private fun callbackToolbar() {
        binding.toolbarMain.setOnMenuItemClickListener {
            it.onNavDestinationSelected(navController)
        }
    }

    private fun configureToolbar() {
        navController.addOnDestinationChangedListener { _: NavController, navDestination: NavDestination, _: Bundle? ->
            if (navDestination.id == R.id.fragmentSplash) {
                binding.appBarLayoutMain.visibility = View.GONE
                binding.bottomNavigationViewMain.visibility = View.GONE
            } else {
                binding.appBarLayoutMain.visibility = View.VISIBLE
                binding.bottomNavigationViewMain.visibility = View.VISIBLE
            }
        }
    }
}