package com.br.douglasalipio.domain.interactors

import com.br.douglasalipio.domain.PosterFeedRepository
import com.br.douglasalipio.domain.states.TotalCountNumberState
import com.br.douglasalipio.domain.states.TotalPostsState

class GetCountNumbersUseCase(private val repository: PosterFeedRepository) {

    suspend fun execute(): TotalCountNumberState {
        val totalPost = repository.getCountNumbers()
        return try {
            TotalCountNumberState.Loaded(totalPost)
        } catch (exception: Throwable) {
            TotalCountNumberState.Fail
        }
    }
}