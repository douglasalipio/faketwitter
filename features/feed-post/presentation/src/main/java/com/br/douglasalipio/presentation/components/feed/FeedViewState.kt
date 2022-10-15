package com.br.douglasalipio.presentation.components.feed

import com.br.douglasalipio.domain.entities.Post

sealed class FeedViewState {

    object LoadFail : FeedViewState()
    data class Loaded(val posts: List<Post>) : FeedViewState()
}