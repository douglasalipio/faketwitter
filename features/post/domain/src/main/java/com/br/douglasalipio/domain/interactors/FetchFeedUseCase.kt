package com.br.douglasalipio.domain.interactors

import com.br.douglasalipio.domain.PosterRepository
import com.br.douglasalipio.domain.entities.Post
import com.br.douglasalipio.domain.states.PosterListState

class FetchFeedUseCase(private val repository: PosterRepository) {

    suspend fun execute(): PosterListState {
        val posts = repository.fetchFeed()
        if (posts.isEmpty())
            return PosterListState.LoadFail

        return PosterListState.Loaded(posts)
    }
}