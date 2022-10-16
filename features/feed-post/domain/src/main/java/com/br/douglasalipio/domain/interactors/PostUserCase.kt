package com.br.douglasalipio.domain.interactors

import com.br.douglasalipio.domain.PosterFeedRepository
import com.br.douglasalipio.domain.entities.Post
import com.br.douglasalipio.domain.entities.PostType
import com.br.douglasalipio.domain.states.PostContentState

class PostUserCase(private val repository: PosterFeedRepository) {

    suspend fun execute(params: Params): PostContentState {

        return try {
            val profile = repository.getDefaultUserProfile()
            val totalPostList =
                repository.postContent(Post(params.content, profile, params.postType))
            PostContentState.Posted(totalPostList)
        } catch (exception: Throwable) {
            PostContentState.Fail
        }
    }

    data class Params(
        val content: String,
        val postType: PostType
    )
}