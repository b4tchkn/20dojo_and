package jp.co.cyberagent.dojo2020.ui.profile

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.utils.ColorTemplate
import jp.co.cyberagent.dojo2020.R
import jp.co.cyberagent.dojo2020.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {
    private lateinit var binding: FragmentProfileBinding

    private val viewModel: ProfileViewModel by viewModels {
        ProfileViewModelFactory(this, Bundle(), requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            profileToHomeButton.setOnClickListener {
                findNavController().navigate(R.id.action_profileFragment_to_homeFragment)
            }

            viewModel.liveData.observe(viewLifecycleOwner) {
                profileUserName.text = it.name

                val url =
                    "https://raw.githubusercontent.com/bumptech/glide/master/static/glide_logo.png"
                Glide.with(view).load(url).into(profileIcon)

                val twitterAccount = it.accountList?.first { it.serviceName == "twitter" }
                profileTwitterId.text = twitterAccount?.id
                profileTwitterUrl.text = twitterAccount?.url

                val githubAccount = it.accountList?.first { it.serviceName == "github" }
                profileGithubId.text = githubAccount?.id
                profileGithubUrl.text = githubAccount?.url
            }

            reloadButton.setOnClickListener {
                viewModel.fetchUserData()
                //viewModel.fetchStudyTime()
                setupPieChart()
            }
        }
    }

    private fun setupPieChart() {

        val times = listOf(15, 45, 25, 35)
        val tags = listOf("japanese", "math", "sciense", "english")

        //PieEntriesのリストを作成する
        val pieEntries: MutableList<PieEntry> = ArrayList()

        (times zip tags).forEach {
            pieEntries.add(PieEntry(it.first.toFloat(), it.second))
        }

        val dataSet = PieDataSet(pieEntries, "category")
        dataSet.setColors(*ColorTemplate.JOYFUL_COLORS)
        dataSet.valueTextSize = 12f
        dataSet.valueTextColor = Color.WHITE
        //dataSet.setDrawValues(false) // 数値を削除するか

        val data = PieData(dataSet)

        //PieChartを取得する
        val pieChart = binding.pieChart
        pieChart.data = data
        pieChart.setEntryLabelTextSize(13f)
        pieChart.setEntryLabelColor(Color.BLACK)
        pieChart.centerText = "statistics"
        pieChart.setCenterTextSize(15f)
        pieChart.animateY(750)
        pieChart.invalidate() //更新
    }
}