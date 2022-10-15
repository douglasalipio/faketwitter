package com.br.douglasalipio.presentation.components.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import by.kirich1409.viewbindingdelegate.viewBinding
import com.br.douglasalipio.domain.entities.UserProfile
import com.br.douglasalipio.presentation.R
import com.br.douglasalipio.presentation.databinding.ProfileFragmentBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProfileFragment : Fragment(R.layout.profile_fragment) {

    private val viewBinding by viewBinding(ProfileFragmentBinding::bind)
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
                    showUserProfile(state.defaultUserProfile)
                    viewModel.loadUserTotalPosts(0)
                }
            }
        }
    }

    private fun showTotalUserPosts(value: Int) {
        viewBinding.totalPosts.text = value.toString()
    }

    private fun showUserProfile(userProfile: UserProfile) {
        viewBinding.joinedDate.text = userProfile.dateJoined
        viewBinding.userName.text = userProfile.username
    }
}