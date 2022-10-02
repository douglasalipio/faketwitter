package com.br.douglasalipio.presentation.components.states

import com.br.douglasalipio.domain.entities.UserProfile

sealed class UserProfileFragmentState {

    object LoadFail : UserProfileFragmentState()
    data class TotalUserLoaded(val value: Int) : UserProfileFragmentState()
    data class Loaded(val defaultUserProfile: UserProfile) : UserProfileFragmentState()
}