package com.br.douglasalipio.domain.interactors

import com.br.douglasalipio.domain.PosterFeedRepository
import com.br.douglasalipio.domain.states.ProfileState

class GetDefaultProfileUseCase(private val repository: PosterFeedRepository) {

    suspend fun execute(): ProfileState {
        val posts = repository.getDefaultUserProfile()
        return try {
            ProfileState.Loaded(posts)
        } catch (exception: Throwable) {
            ProfileState.LoadFail
        }
    }
}