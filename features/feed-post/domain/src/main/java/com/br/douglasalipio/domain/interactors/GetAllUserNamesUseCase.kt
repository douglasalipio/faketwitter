package com.br.douglasalipio.domain.interactors

import com.br.douglasalipio.domain.PosterFeedRepository
import com.br.douglasalipio.domain.states.GetAllUsernamesState

class GetAllUserNamesUseCase(private val repository: PosterFeedRepository) {

    suspend fun execute(): GetAllUsernamesState {
        val usernames = repository.getAllNames()
        if (usernames.isEmpty())
            return GetAllUsernamesState.Fail

        return GetAllUsernamesState.Loaded(usernames)
    }
}