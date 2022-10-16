package com.br.douglasalipio.domain.interactors

import com.br.douglasalipio.domain.PosterFeedRepository
import com.br.douglasalipio.domain.states.TotalPostsState

class GetTotalUserPostsUseCase(private val repository: PosterFeedRepository) {

    suspend fun execute(): TotalPostsState {
        val totalPost = repository.getTotalPosts()
        return try {
            TotalPostsState.Loaded(totalPost)
        } catch (exception: Throwable) {
            TotalPostsState.Fail
        }
    }
}