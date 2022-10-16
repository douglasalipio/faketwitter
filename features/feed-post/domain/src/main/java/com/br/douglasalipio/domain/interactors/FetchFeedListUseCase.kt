package com.br.douglasalipio.domain.interactors

import com.br.douglasalipio.domain.PosterFeedRepository
import com.br.douglasalipio.domain.states.FeedListState

class FetchFeedListUseCase(private val repository: PosterFeedRepository) {

    suspend fun execute(): FeedListState {
        val posts = repository.fetchFeed()
        if (posts.isEmpty())
            return FeedListState.Fail

        return FeedListState.Loaded(posts)
    }
}