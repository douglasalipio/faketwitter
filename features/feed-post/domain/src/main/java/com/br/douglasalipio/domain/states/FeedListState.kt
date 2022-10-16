package com.br.douglasalipio.domain.states

import com.br.douglasalipio.domain.entities.Tweet

sealed class FeedListState {

    object Fail : FeedListState()

    data class Loaded(val tweets: List<Tweet>) : FeedListState()
}