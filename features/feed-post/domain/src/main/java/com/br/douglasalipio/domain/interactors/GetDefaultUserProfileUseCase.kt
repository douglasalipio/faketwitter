package com.br.douglasalipio.domain.interactors

import com.br.douglasalipio.domain.PosterRepository
import com.br.douglasalipio.domain.states.PosterListState
import com.br.douglasalipio.domain.states.UserProfileState

class GetDefaultUserProfileUseCase(private val repository: PosterRepository) {

    suspend fun execute(): UserProfileState {
        val posts = repository.getDefaultUserProfile()
        return try {
            UserProfileState.Loaded(posts)
        } catch (exception: Throwable) {
            UserProfileState.LoadFail
        }
    }
}