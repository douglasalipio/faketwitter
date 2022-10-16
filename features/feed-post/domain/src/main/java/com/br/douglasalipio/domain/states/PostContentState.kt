package com.br.douglasalipio.domain.states

import com.br.douglasalipio.domain.entities.Post

sealed class PostContentState {
    object Fail : PostContentState()
    data class Posted(val tweets: List<Post>) : PostContentState()
}