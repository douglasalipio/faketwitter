package com.br.douglasalipio.domain.states

import com.br.douglasalipio.domain.entities.Post

sealed class FeedListState {

    object Fail : FeedListState()

    data class Loaded(val tweets: List<Post>) : FeedListState()
}