package jp.co.cyberagent.dojo2020

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI.setupWithNavController
import androidx.navigation.ui.setupActionBarWithNavController
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



//        pager.adapter = TabAdapter(supportFragmentManager,this)
//        tab_layout.setupWithViewPager(pager)

        val navController = findNavController(R.id.nav_host_fragment)
        setupWithNavController(bottom_navigation, navController)


    }
}