package com.br.douglasalipio.presentation.components.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.lifecycle.Observer
import by.kirich1409.viewbindingdelegate.viewBinding
import com.br.douglasalipio.domain.entities.UserProfile
import com.br.douglasalipio.presentation.R
import com.br.douglasalipio.presentation.components.states.UserProfileFragmentState
import com.br.douglasalipio.presentation.components.viewmodels.UserProfileViewModel
import com.br.douglasalipio.presentation.databinding.UserProfileFragmentBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class UserProfileFragment : Fragment(R.layout.user_profile_fragment) {

    private val viewBinding by viewBinding(UserProfileFragmentBinding::bind)
    private val viewModel: UserProfileViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpEvents()
        viewModel.loadUserProfile()
    }

    private fun setUpEvents() {
        viewModel.viewState.observe(viewLifecycleOwner, Observer { state ->
            when (state) {
                is UserProfileFragmentState.LoadFail -> {}
                is UserProfileFragmentState.TotalUserLoaded -> showTotalUserPosts(state.value)
                is UserProfileFragmentState.Loaded -> {
                    showUserProfile(state.defaultUserProfile)
                    viewModel.loadUserTotalPosts(0)
                }
            }
        })
    }

    private fun showTotalUserPosts(value: Int) {
        viewBinding.totalPosts.text = value.toString()
    }

    private fun showUserProfile(userProfile: UserProfile) {
        viewBinding.joinedDate.text = userProfile.dateJoined
        viewBinding.userName.text = userProfile.username
    }
}