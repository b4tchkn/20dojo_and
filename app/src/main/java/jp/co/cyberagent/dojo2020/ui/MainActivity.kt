package jp.co.cyberagent.dojo2020.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import jp.co.cyberagent.dojo2020.databinding.ActivityMainBinding
import jp.co.cyberagent.dojo2020.ui.home.HomeViewModel
import jp.co.cyberagent.dojo2020.ui.home.HomeViewModelFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val homeViewModel by viewModels<HomeViewModel> {
        HomeViewModelFactory(this, Bundle(), this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {

            val memoAdapter = MemoAdapter()

            val linearLayoutManager = LinearLayoutManager(
                this@MainActivity,
                LinearLayoutManager.VERTICAL,
                false
            )

            recyclerView.apply {
                layoutManager = linearLayoutManager
                adapter = memoAdapter
            }
        }

    }
}