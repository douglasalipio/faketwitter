package com.br.douglasalipio.domain.states

import com.br.douglasalipio.domain.entities.Profile

sealed class ProfileState {

    object LoadFail : ProfileState()
    data class Loaded(val defaultUser: Profile) : ProfileState()
    data class TotalPostLoaded(val value: Int) : ProfileState()
}