package com.br.douglasalipio.presentation.components.feed

import com.br.douglasalipio.domain.entities.Tweet

sealed class FeedViewState {

    object LoadFail : FeedViewState()
    data class Loaded(val tweets: List<Tweet>) : FeedViewState()
}