package com.br.douglasalipio.presentation.components.profile

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.br.douglasalipio.domain.interactors.GetCountNumbersUseCase
import com.br.douglasalipio.domain.interactors.GetDefaultProfileUseCase
import com.br.douglasalipio.domain.states.ProfileState
import com.br.douglasalipio.domain.states.TotalCountNumberState
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val getDefaultUserProfile: GetDefaultProfileUseCase,
    private val getCountNumbersUseCase: GetCountNumbersUseCase
) : ViewModel() {

    val viewState = MutableLiveData<ProfileViewState>()

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

    fun loadCountNumbers() {
        viewModelScope.launch {
            getCountNumbersUseCase.execute().let { totalPostsState ->
                when (totalPostsState) {
                    is TotalCountNumberState.Loaded -> {
                        viewState.value =
                            ProfileViewState.TotalLoaded(totalPostsState.value)
                    }
                    is TotalCountNumberState.Fail -> {
                        viewState.value = ProfileViewState.LoadFail
                    }
                }
            }
        }
    }
}