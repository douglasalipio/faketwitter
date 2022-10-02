package com.br.douglasalipio.presentation.components.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.br.douglasalipio.domain.entities.Post
import com.br.douglasalipio.domain.entities.UserProfile
import com.br.douglasalipio.presentation.R
import com.br.douglasalipio.presentation.components.states.PosterListFragmentState
import com.br.douglasalipio.presentation.components.states.UserProfileFragmentState
import com.br.douglasalipio.presentation.components.viewAdapters.PosterRecyclerViewAdapter
import com.br.douglasalipio.presentation.components.viewmodels.PosterViewModel
import com.br.douglasalipio.presentation.components.viewmodels.UserProfileViewModel
import com.br.douglasalipio.presentation.databinding.FragmentUserProfileBinding
import com.br.douglasalipio.presentation.databinding.PosterFragmentListBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class UserProfileFragment : Fragment(R.layout.fragment_user_profile) {

    private val viewBinding by viewBinding(FragmentUserProfileBinding::bind)
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