package com.br.douglasalipio.presentation.components.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.br.douglasalipio.domain.interactors.GetDefaultUserProfileUseCase
import com.br.douglasalipio.domain.interactors.GetTotalUserPostsUseCase
import com.br.douglasalipio.domain.states.TotalPostsState
import com.br.douglasalipio.domain.states.UserProfileState
import com.br.douglasalipio.presentation.components.states.UserProfileFragmentState
import kotlinx.coroutines.launch

class UserProfileViewModel(
    private val getDefaultUserProfile: GetDefaultUserProfileUseCase,
    private val getTotalUserPostsUseCase: GetTotalUserPostsUseCase
) : ViewModel() {

    val viewState = MutableLiveData<UserProfileFragmentState>()
    val totalPostsViewState = MutableLiveData<TotalPostsState>()

    fun loadUserProfile() {
        viewModelScope.launch {

            getDefaultUserProfile.execute().let { userProfileState ->
                when (userProfileState) {
                    is UserProfileState.LoadFail -> viewState.value =
                        UserProfileFragmentState.LoadFail

                    is UserProfileState.Loaded -> viewState.value =
                        UserProfileFragmentState.Loaded(userProfileState.defaultUser)
                    else -> {}
                }
            }
        }
    }

    fun loadUserTotalPosts(userId: Int) {
        val params = GetTotalUserPostsUseCase.Params(userId)

        viewModelScope.launch {
            getTotalUserPostsUseCase.execute(params).let { totalPostsState ->
                when (totalPostsState) {
                    is TotalPostsState.Loaded -> {
                        viewState.value =
                            UserProfileFragmentState.TotalUserLoaded(totalPostsState.value)
                    }
                    is TotalPostsState.LoadFail -> {
                        viewState.value = UserProfileFragmentState.LoadFail
                    }
                }
            }
        }
    }
}