package com.br.douglasalipio.domain.states

sealed class TotalPostsState {
    object Fail : TotalPostsState()
    data class Loaded(val value: Int) : TotalPostsState()
}