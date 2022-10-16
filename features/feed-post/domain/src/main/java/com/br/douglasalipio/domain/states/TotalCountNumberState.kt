package com.br.douglasalipio.domain.states

sealed class TotalCountNumberState {
    object Fail : TotalCountNumberState()
    data class Loaded(val value: List<Int>) : TotalCountNumberState()
}