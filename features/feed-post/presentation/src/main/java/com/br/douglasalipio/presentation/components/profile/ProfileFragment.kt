package com.br.douglasalipio.presentation.components.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import by.kirich1409.viewbindingdelegate.viewBinding
import com.br.douglasalipio.domain.entities.Profile
import com.br.douglasalipio.presentation.R
import com.br.douglasalipio.presentation.databinding.UserProfileFragmentBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProfileFragment : Fragment(R.layout.user_profile_fragment) {

    private val viewBinding by viewBinding(UserProfileFragmentBinding::bind)
    private val viewModel: ProfileViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpEvents()
        viewModel.loadUserProfile()
    }

    private fun setUpEvents() {
        viewModel.viewState.observe(viewLifecycleOwner) { state ->
            when (state) {
                is ProfileViewState.LoadFail -> {}
                is ProfileViewState.TotalLoaded -> showTotalUserPosts(state.value)
                is ProfileViewState.Loaded -> {
                    showUserProfile(state.defaultProfile)
                    viewModel.loadCountNumbers()
                }
            }
        }
    }

    private fun showTotalUserPosts(value: List<Int>) {
        viewBinding.totalPosts.text = value[0].toString()
        viewBinding.totalQuotePosts.text = value[1].toString()
        viewBinding.totalReposting.text = value[2].toString()
    }

    private fun showUserProfile(profile: Profile) {
        viewBinding.joinedDate.text = profile.dateJoined
        viewBinding.userName.text = profile.username
    }
}