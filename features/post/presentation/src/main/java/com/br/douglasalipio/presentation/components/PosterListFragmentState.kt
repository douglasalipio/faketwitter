package com.br.douglasalipio.presentation.components

import com.br.douglasalipio.domain.entities.Post

sealed class PosterListFragmentState {

    object LoadFail : PosterListFragmentState()
    data class Loaded(val posts: List<Post>) : PosterListFragmentState()
}