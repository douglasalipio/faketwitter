package com.br.douglasalipio.presentation.components.post

sealed class PostViewState {

    object UsernamesLoadFail : PostViewState()
    data class UsernamesLoaded(val usernames: List<String>) : PostViewState()
}