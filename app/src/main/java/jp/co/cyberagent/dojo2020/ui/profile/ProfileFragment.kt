package jp.co.cyberagent.dojo2020.ui.profile

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.utils.ColorTemplate
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
            profileToolBarLayout.profileMaterialToolBar.setNavigationOnClickListener { view ->
                view.findNavController().navigateUp()
            }

            viewModel.firebaseUserInfo.observe(viewLifecycleOwner) { firebaseUser ->
                firebaseUser ?: return@observe

                userNameTextView.text = firebaseUser.name

                Glide.with(view).load(firebaseUser.imageUri).circleCrop().into(iconImageView)
            }


            viewModel.profileLiveData.observe(viewLifecycleOwner) { profile ->
                val twitterAccount = profile.accountList?.first { it.serviceName == "twitter" }
                twitterIdTextView.text = twitterAccount?.id
                twitterUrlTextView.text = twitterAccount?.url

                val githubAccount = profile.accountList?.first { it.serviceName == "github" }
                githubIdTextView.text = githubAccount?.id
                githubUrlTextView.text = githubAccount?.url
            }
        }
    }

    private fun setupPieChart() {

        val times = listOf(15, 45, 25, 35)
        val tags = listOf("japanese", "math", "sciense", "english")

        val pieEntries: MutableList<PieEntry> = ArrayList()

        (times zip tags).forEach {
            pieEntries.add(PieEntry(it.first.toFloat(), it.second))
        }

        val dataSet = PieDataSet(pieEntries, "category")
        dataSet.apply {
            setColors(*ColorTemplate.JOYFUL_COLORS)
            valueTextSize = 12f
            valueTextColor = Color.WHITE
            //setDrawValues(false) // 数値を削除するか
        }

        val data = PieData(dataSet)

        val pieChart = binding.pieChart
        pieChart.apply {
            this.data = data
            setEntryLabelTextSize(13f)
            setEntryLabelColor(Color.BLACK)
            centerText = "statistics"
            setCenterTextSize(15f)
            animateY(750)
            invalidate() //更新
        }
    }
}