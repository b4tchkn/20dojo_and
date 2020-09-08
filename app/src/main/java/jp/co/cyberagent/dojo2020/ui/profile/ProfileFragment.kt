package jp.co.cyberagent.dojo2020.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
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
                profileIconUrl.text = it.iconUrl
                val twitterAccount = it.accountList?.first { it.serviceName == "twitter" }
                profileTwitterId.text = twitterAccount?.id
                profileTwitterUrl.text = twitterAccount?.url
                val githubAccount = it.accountList?.first { it.serviceName == "github" }
                profileGithubId.text = githubAccount?.id
                profileGithubUrl.text = githubAccount?.url
            }

            reloadButton.setOnClickListener {
                viewModel.fetchUserData()
            }
        }

    }
}