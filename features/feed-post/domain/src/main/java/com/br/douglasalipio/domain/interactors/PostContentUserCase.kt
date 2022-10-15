package com.br.douglasalipio.domain.interactors

import com.br.douglasalipio.domain.PosterRepository
import com.br.douglasalipio.domain.entities.Post
import com.br.douglasalipio.domain.entities.UserProfile
import com.br.douglasalipio.domain.states.PostContentState
import com.br.douglasalipio.domain.states.TotalPostsState

class PostContentUserCase(private val repository: PosterRepository) {

    suspend fun execute(params: Params): PostContentState {
        val totalPost = repository.postContent(Post(params.content, params.userProfile))
        return try {
            PostContentState.Loaded(totalPost)
        } catch (exception: Throwable) {
            PostContentState.LoadFail
        }
    }

    data class Params(val userProfile: UserProfile, val content: String)
}