package com.br.douglasalipio.domain.states

sealed class TotalTweetsState {
    object LoadFail : TotalTweetsState()
    data class Loaded(val value: Int) : TotalTweetsState()
}