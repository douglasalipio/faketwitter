package com.br.douglasalipio.presentation.components.profile

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.br.douglasalipio.domain.interactors.GetDefaultUserProfileUseCase
import com.br.douglasalipio.domain.interactors.GetTotalUserPostsUseCase
import com.br.douglasalipio.domain.states.TotalPostsState
import com.br.douglasalipio.domain.states.UserProfileState
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val getDefaultUserProfile: GetDefaultUserProfileUseCase,
    private val getTotalUserPostsUseCase: GetTotalUserPostsUseCase
) : ViewModel() {

    val viewState = MutableLiveData<ProfileViewState>()
    val totalPostsViewState = MutableLiveData<TotalPostsState>()

    fun loadUserProfile() {
        viewModelScope.launch {

            getDefaultUserProfile.execute().let { userProfileState ->
                when (userProfileState) {
                    is UserProfileState.LoadFail -> viewState.value =
                        ProfileViewState.LoadFail

                    is UserProfileState.Loaded -> viewState.value =
                        ProfileViewState.Loaded(userProfileState.defaultUser)
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
                            ProfileViewState.TotalLoaded(totalPostsState.value)
                    }
                    is TotalPostsState.LoadFail -> {
                        viewState.value = ProfileViewState.LoadFail
                    }
                }
            }
        }
    }
}