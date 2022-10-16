package com.br.douglasalipio.presentation.components.post

sealed class PostViewState {

    object UserLoadFail : PostViewState()
    data class UserLoaded(val usernames: List<String>) : PostViewState()
}