package com.br.douglasalipio.domain.interactors

import com.br.douglasalipio.domain.PosterFeedRepository
import com.br.douglasalipio.domain.states.PosterListState

class FetchFeedListUseCase(private val repository: PosterFeedRepository) {

    suspend fun execute(): PosterListState {
        val posts = repository.fetchFeed()
        if (posts.isEmpty())
            return PosterListState.LoadFail

        return PosterListState.Loaded(posts)
    }
}