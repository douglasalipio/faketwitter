package com.br.douglasalipio.domain.states

sealed class GetAllUsernamesState {

    object Fail : GetAllUsernamesState()

    data class Loaded(val usernames: List<String>) : GetAllUsernamesState()
}