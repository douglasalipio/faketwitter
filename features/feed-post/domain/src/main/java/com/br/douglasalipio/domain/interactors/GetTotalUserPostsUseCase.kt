package com.br.douglasalipio.domain.interactors

import com.br.douglasalipio.domain.PosterRepository
import com.br.douglasalipio.domain.states.TotalPostsState
import com.br.douglasalipio.domain.states.UserProfileState

class GetTotalUserPostsUseCase(private val repository: PosterRepository) {

    suspend fun execute(params: Params): TotalPostsState {
        val totalPost = repository.getTotalUserPosts(params.userId)
        return try {
            TotalPostsState.Loaded(totalPost)
        } catch (exception: Throwable) {
            TotalPostsState.LoadFail
        }
    }

    data class Params(val userId: Int)
}