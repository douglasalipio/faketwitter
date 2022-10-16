package com.br.douglasalipio.domain.interactors

import com.br.douglasalipio.domain.PosterFeedRepository
import com.br.douglasalipio.domain.states.TotalPostsState

class GetTotalUserTweetsUseCase(private val repository: PosterFeedRepository) {

    suspend fun execute(params: Params): TotalPostsState {
        val totalPost = repository.getTotalUserPosts(params.userId)
        return try {
            TotalPostsState.Loaded(totalPost)
        } catch (exception: Throwable) {
            TotalPostsState.Fail
        }
    }

    data class Params(val userId: Int)
}