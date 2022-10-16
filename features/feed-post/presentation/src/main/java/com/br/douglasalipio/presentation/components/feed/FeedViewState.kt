package com.br.douglasalipio.presentation.components.feed

import com.br.douglasalipio.domain.entities.Post

sealed class FeedViewState {

    object Fail : FeedViewState()
    data class FeedListLoaded(val tweets: List<Post>) : FeedViewState()
    object PostContentAllowed : FeedViewState()
    object PostContentNotAllowed : FeedViewState()
}