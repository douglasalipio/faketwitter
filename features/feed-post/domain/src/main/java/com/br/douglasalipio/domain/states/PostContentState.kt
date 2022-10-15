package com.br.douglasalipio.domain.states

import com.br.douglasalipio.domain.entities.Post
import com.br.douglasalipio.domain.entities.UserProfile

sealed class PostContentState {
    object LoadFail : PostContentState()
    data class Loaded(val posts: List<Post>) : PostContentState()
}