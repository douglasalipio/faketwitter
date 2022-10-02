package com.br.douglasalipio.domain.states

import com.br.douglasalipio.domain.entities.UserProfile

sealed class TotalPostsState {
    object LoadFail : TotalPostsState()
    data class Loaded(val value: Int) : TotalPostsState()
}