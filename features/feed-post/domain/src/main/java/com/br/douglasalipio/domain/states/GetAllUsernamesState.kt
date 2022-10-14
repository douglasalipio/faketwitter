package com.br.douglasalipio.domain.states

sealed class GetAllUsernamesState {

    object LoadFail : GetAllUsernamesState()

    data class Loaded(val usernames: List<String>) : GetAllUsernamesState()
}