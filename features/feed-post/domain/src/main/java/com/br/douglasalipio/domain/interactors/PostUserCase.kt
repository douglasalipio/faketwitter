package com.br.douglasalipio.domain.interactors

import com.br.douglasalipio.domain.PosterFeedRepository
import com.br.douglasalipio.domain.entities.Profile
import com.br.douglasalipio.domain.entities.Tweet
import com.br.douglasalipio.domain.states.PostContentState

class PostUserCase(private val repository: PosterFeedRepository) {

    suspend fun execute(params: Params): PostContentState {

        return try {
            val profile: Profile = if (params.hasReposting) {
                repository.getDefaultUserProfile()
            } else {
                repository.getUserById(params.itemPosition)
            }
            val totalTweet =
                repository.postContent(Tweet(params.content, profile, params.hasReposting))
            PostContentState.Posted(totalTweet)
        } catch (exception: Throwable) {
            PostContentState.Fail
        }
    }

    data class Params(
        val content: String,
        val hasReposting: Boolean,
        val itemPosition: Int
    )
}