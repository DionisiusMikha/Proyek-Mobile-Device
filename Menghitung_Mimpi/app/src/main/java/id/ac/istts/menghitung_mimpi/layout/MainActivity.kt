package id.ac.istts.menghitung_mimpi.layout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import androidx.fragment.app.FragmentContainerView
import id.ac.istts.menghitung_mimpi.R

class MainActivity : AppCompatActivity() {
    lateinit var container: FragmentContainerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        container = findViewById(R.id.fragmentContainer)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.bottom_nav, menu)
        return super.onCreateOptionsMenu(menu)
    }
}