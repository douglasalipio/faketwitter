package com.br.douglasalipio.domain.states

import com.br.douglasalipio.domain.entities.Post

sealed class PosterListState {

    object LoadFail : PosterListState()
    data class Loaded(val posts: List<Post>) : PosterListState()
}