package com.br.douglasalipio.domain.interactors

import com.br.douglasalipio.domain.PosterFeedRepository
import com.br.douglasalipio.domain.states.TotalTweetsState

class GetTotalUserTweetsUseCase(private val repository: PosterFeedRepository) {

    suspend fun execute(params: Params): TotalTweetsState {
        val totalPost = repository.getTotalUserPosts(params.userId)
        return try {
            TotalTweetsState.Loaded(totalPost)
        } catch (exception: Throwable) {
            TotalTweetsState.LoadFail
        }
    }

    data class Params(val userId: Int)
}