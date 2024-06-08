package id.ac.istts.menghitung_mimpi.layout

import android.content.Intent
import android.os.Bundle
import android.view.MotionEvent
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import id.ac.istts.menghitung_mimpi.R
import id.ac.istts.menghitung_mimpi.ViewPagerAdapter

class IntroSliderActivity : AppCompatActivity() {
    private lateinit var viewPagerAdapter: ViewPagerAdapter
    private var isLastPageReached = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro_slider)

        val images = intArrayOf(
            R.drawable.manodinero,
            R.drawable.backcard
        )

        val text = intArrayOf(
            R.string.text1,
            R.string.text2
        )

        val viewPager = findViewById<ViewPager2>(R.id.viewPager)
        viewPagerAdapter = ViewPagerAdapter(this, images, text)
        viewPager.adapter = viewPagerAdapter

        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                if (position == viewPagerAdapter.itemCount - 1) {
                    isLastPageReached = true
                } else {
                    isLastPageReached = false
                }
            }
        })

        viewPager.isUserInputEnabled = true
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        if (isLastPageReached && ev?.action == MotionEvent.ACTION_DOWN) {
            startSignupActivity()
            return true
        }
        return super.dispatchTouchEvent(ev)
    }

    private fun startSignupActivity() {
        val intent = Intent(this, RegisterActivity::class.java)
        startActivity(intent)
        finish()
    }
}
