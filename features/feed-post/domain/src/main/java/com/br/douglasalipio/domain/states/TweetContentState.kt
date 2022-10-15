package com.br.douglasalipio.domain.states

import com.br.douglasalipio.domain.entities.Tweet

sealed class TweetContentState {
    object LoadFail : TweetContentState()
    data class Loaded(val tweets: List<Tweet>) : TweetContentState()
}