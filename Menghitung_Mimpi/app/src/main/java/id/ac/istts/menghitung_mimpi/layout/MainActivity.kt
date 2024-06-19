package id.ac.istts.menghitung_mimpi.layout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import id.ac.istts.menghitung_mimpi.R

class MainActivity : AppCompatActivity() {
    lateinit var container: FragmentContainerView
    lateinit var bottomNavigation: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        container = findViewById(R.id.fragmentContainer)
        bottomNavigation = findViewById(R.id.bottomNavigationView)
        replaceFragment(HomeFragment())

        bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.homeFragment -> replaceFragment(HomeFragment())
                R.id.riwayatFragment -> replaceFragment(HistoryFragment())
                R.id.cardFragment -> replaceFragment(CardFragment())
                R.id.profileFragment -> replaceFragment(ProfileFragment())
                else -> false
            }
            true
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.bottom_nav, menu)
        return super.onCreateOptionsMenu(menu)
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentContainer, fragment)
        fragmentTransaction.commit()
    }
}
