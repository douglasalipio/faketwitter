package com.br.douglasalipio.presentation.components.feed

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.br.douglasalipio.domain.interactors.FetchFeedListUseCase
import com.br.douglasalipio.domain.interactors.GetTotalUserPostsUseCase
import com.br.douglasalipio.domain.states.FeedListState
import com.br.douglasalipio.domain.states.TotalPostsState
import kotlinx.coroutines.launch

class FeedViewModel(
    private val fetchFeedListUseCase: FetchFeedListUseCase,
    private val getTotalUserPostsUseCase: GetTotalUserPostsUseCase
) : ViewModel() {

    val viewState = MutableLiveData<FeedViewState>()

    fun loadTotalUserPosts() {
        viewModelScope.launch {
            getTotalUserPostsUseCase.execute().let { totalUserPostsState ->
                    val limit = 5
                    if (totalUserPostsState is TotalPostsState.Loaded) {
                        if (totalUserPostsState.value >= limit) {
                            viewState.value = FeedViewState.PostContentNotAllowed
                        } else {
                            viewState.value = FeedViewState.PostContentAllowed
                        }
                    }
                }
        }
    }

    fun loadFeedList() {
        viewModelScope.launch {

            fetchFeedListUseCase.execute().let { posterListState ->
                when (posterListState) {
                    is FeedListState.Fail -> viewState.value = FeedViewState.Fail

                    is FeedListState.Loaded -> viewState.value =
                        FeedViewState.FeedListLoaded(posterListState.tweets)
                }
            }
        }
    }


}