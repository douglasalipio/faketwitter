package com.br.douglasalipio.presentation.components.profile

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.br.douglasalipio.domain.interactors.GetDefaultProfileUseCase
import com.br.douglasalipio.domain.interactors.GetTotalUserTweetsUseCase
import com.br.douglasalipio.domain.states.TotalPostsState
import com.br.douglasalipio.domain.states.ProfileState
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val getDefaultUserProfile: GetDefaultProfileUseCase,
    private val getTotalUserTweetsUseCase: GetTotalUserTweetsUseCase
) : ViewModel() {

    val viewState = MutableLiveData<ProfileViewState>()
    val totalPostsViewState = MutableLiveData<TotalPostsState>()

    fun loadUserProfile() {
        viewModelScope.launch {

            getDefaultUserProfile.execute().let { userProfileState ->
                when (userProfileState) {
                    is ProfileState.LoadFail -> viewState.value =
                        ProfileViewState.LoadFail

                    is ProfileState.Loaded -> viewState.value =
                        ProfileViewState.Loaded(userProfileState.defaultUser)
                    else -> {}
                }
            }
        }
    }

    fun loadUserTotalPosts(userId: Int) {
        val params = GetTotalUserTweetsUseCase.Params(userId)

        viewModelScope.launch {
            getTotalUserTweetsUseCase.execute(params).let { totalPostsState ->
                when (totalPostsState) {
                    is TotalPostsState.Loaded -> {
                        viewState.value =
                            ProfileViewState.TotalLoaded(totalPostsState.value)
                    }
                    is TotalPostsState.Fail -> {
                        viewState.value = ProfileViewState.LoadFail
                    }
                }
            }
        }
    }
}