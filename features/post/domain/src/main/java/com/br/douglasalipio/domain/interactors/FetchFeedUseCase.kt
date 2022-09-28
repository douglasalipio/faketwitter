package com.br.douglasalipio.domain.interactors

import com.br.douglasalipio.domain.PosterRepository

class FetchFeedUseCase(private val repository: PosterRepository) {

    suspend fun execute() = repository.fetchFeed()
}