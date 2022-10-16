package com.br.douglasalipio.presentation.components.feed

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.br.douglasalipio.domain.interactors.FetchFeedListUseCase
import com.br.douglasalipio.domain.states.FeedListState
import kotlinx.coroutines.launch

class FeedViewModel(private val fetchFeedListUseCase: FetchFeedListUseCase) : ViewModel() {

    val viewState = MutableLiveData<FeedViewState>()

    fun loadFeedList() {
        viewModelScope.launch {

            fetchFeedListUseCase.execute().let { posterListState ->
                when (posterListState) {
                    is FeedListState.Fail -> viewState.value = FeedViewState.LoadFail

                    is FeedListState.Loaded -> viewState.value =
                        FeedViewState.Loaded(posterListState.tweets)
                }
            }
        }
    }

}