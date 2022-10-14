package com.br.douglasalipio.domain.interactors

import com.br.douglasalipio.domain.PosterRepository
import com.br.douglasalipio.domain.states.GetAllUsernamesState

class GetAllUserNamesUseCase(private val repository: PosterRepository) {

    suspend fun execute(): GetAllUsernamesState {
        val usernames = repository.getAllNames()
        if (usernames.isEmpty())
            return GetAllUsernamesState.LoadFail

        return GetAllUsernamesState.Loaded(usernames)
    }
}