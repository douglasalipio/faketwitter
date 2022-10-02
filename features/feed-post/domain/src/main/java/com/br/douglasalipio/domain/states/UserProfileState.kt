package com.br.douglasalipio.domain.states

import com.br.douglasalipio.domain.entities.Post
import com.br.douglasalipio.domain.entities.UserProfile

sealed class UserProfileState {

    object LoadFail : UserProfileState()
    data class Loaded(val defaultUser: UserProfile) : UserProfileState()
    data class TotalPostLoaded(val value: Int) : UserProfileState()
}