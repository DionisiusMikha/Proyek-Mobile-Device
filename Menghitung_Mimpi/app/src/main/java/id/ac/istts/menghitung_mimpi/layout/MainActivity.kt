package id.ac.istts.menghitung_mimpi.layout

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainerView
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.google.android.material.bottomnavigation.BottomNavigationView
import id.ac.istts.menghitung_mimpi.R

class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {

    private lateinit var navController: NavController
    private lateinit var bottomNavigation: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        container = findViewById(R.id.fragmentContainer)
        bottomNavigation = findViewById(R.id.bottomNavigationView)
        replaceFragment(PernikahanFragment())

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.fragmentContainer) as NavHostFragment
        navController = navHostFragment.navController

        bottomNavigation = findViewById(R.id.bottomNavigationView)
        NavigationUI.setupWithNavController(bottomNavigation, navController)

        // Set the listener for BottomNavigationView
        bottomNavigation.setOnNavigationItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.homeFragment -> {
                navController.navigate(R.id.homeFragment)
                true
            }
            R.id.historyFragment -> {
                navController.navigate(R.id.historyFragment)
                true
            }
            R.id.cardFragment2 -> {
                navController.navigate(R.id.cardFragment2)
                true
            }
            R.id.profileFragment2 -> {
                navController.navigate(R.id.profileFragment2)
                true
            }
            else -> false
        }
    }
}
