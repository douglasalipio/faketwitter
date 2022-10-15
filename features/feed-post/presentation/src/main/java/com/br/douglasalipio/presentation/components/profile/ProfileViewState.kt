package com.br.douglasalipio.presentation.components.profile

import com.br.douglasalipio.domain.entities.UserProfile

sealed class ProfileViewState {

    object LoadFail : ProfileViewState()
    data class TotalLoaded(val value: Int) : ProfileViewState()
    data class Loaded(val defaultUserProfile: UserProfile) : ProfileViewState()
}