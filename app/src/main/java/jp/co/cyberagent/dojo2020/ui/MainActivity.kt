package jp.co.cyberagent.dojo2020.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.observe
import jp.co.cyberagent.dojo2020.data.model.Memo
import jp.co.cyberagent.dojo2020.databinding.ActivityMainBinding
import jp.co.cyberagent.dojo2020.ui.home.HomeViewModel
import jp.co.cyberagent.dojo2020.ui.home.HomeViewModelFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val viewModel: HomeViewModel by viewModels {
        HomeViewModelFactory(this, Bundle(), this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {

            viewModel.liveData.observe(this@MainActivity) {
                val firstMemo = it.randomOrNull() ?: return@observe

                firstMemoTitle.text = firstMemo.title
                firstMemoContent.text = firstMemo.contents
                firstMemoTime.text = firstMemo.time.toString()

                val secondMemo = it.drop(1).randomOrNull() ?: return@observe

                secondMemoTitle.text = secondMemo.title
                secondMemoContent.text = secondMemo.contents
                secondMemoTime.text = secondMemo.time.toString()
            }

            reloadButton.setOnClickListener {
                viewModel.fetchMemoList()
            }

            saveButton.setOnClickListener {
                val title = titleEditText.text.toString()
                val memo = createWithTitle(title)

                viewModel.saveMemo(memo)
            }
        }
    }

    private fun createWithTitle(title: String): Memo {
        return Memo(title, "contents", 0.256)
    }
}