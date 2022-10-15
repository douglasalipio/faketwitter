package com.br.douglasalipio.domain.interactors

import com.br.douglasalipio.domain.PosterFeedRepository
import com.br.douglasalipio.domain.entities.Tweet
import com.br.douglasalipio.domain.entities.Profile
import com.br.douglasalipio.domain.states.TweetContentState

class TweetContentUserCase(private val repository: PosterFeedRepository) {

    suspend fun execute(params: Params): TweetContentState {
        val totalTweet = repository.postContent(Tweet(params.content, params.profile))
        return try {
            TweetContentState.Loaded(totalTweet)
        } catch (exception: Throwable) {
            TweetContentState.LoadFail
        }
    }

    data class Params(val profile: Profile, val content: String)
}