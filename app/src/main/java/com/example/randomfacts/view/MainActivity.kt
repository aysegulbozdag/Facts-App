package com.example.randomfacts.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.randomfacts.R
import com.example.randomfacts.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
      //  setContentView(R.layout.activity_main)

        val homeFragment = HomeFragment();
        val bookmarkFragment = BookmarkFragment()
        val settingsFragment = SettingsFragment()

        makeCurrentFragment(homeFragment)

        binding.bottomNavigationView.setOnNavigationItemReselectedListener {
            when(it.itemId){
                R.id.home_icon-> makeCurrentFragment(homeFragment)
                R.id.bookmarks_icon-> makeCurrentFragment(bookmarkFragment)
                R.id.setting_icon-> makeCurrentFragment(settingsFragment)
            }
        }
    }

    private fun makeCurrentFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fl, fragment)
                .commit()
        }
    }

}