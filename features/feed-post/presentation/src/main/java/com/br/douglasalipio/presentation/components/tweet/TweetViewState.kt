package com.br.douglasalipio.presentation.components.tweet

sealed class TweetViewState {

    object UsernamesLoadFail : TweetViewState()
    data class UsernamesLoaded(val usernames: List<String>) : TweetViewState()
}