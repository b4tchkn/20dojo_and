package jp.co.cyberagent.dojo2020.ui

import android.os.Bundle
import android.view.Menu
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import jp.co.cyberagent.dojo2020.R
import jp.co.cyberagent.dojo2020.databinding.ActivityMainBinding
import jp.co.cyberagent.dojo2020.ui.create.MemoCreateFragment
import jp.co.cyberagent.dojo2020.ui.home.HomeViewModel
import jp.co.cyberagent.dojo2020.ui.home.HomeViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_memo_create.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val homeViewModel by viewModels<HomeViewModel> {
        HomeViewModelFactory(this, Bundle(), this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)


    }



}