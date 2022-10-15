package com.br.douglasalipio.presentation.components.feed

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.br.douglasalipio.domain.interactors.FetchFeedUseCase
import com.br.douglasalipio.domain.states.PosterListState
import kotlinx.coroutines.launch

class FeedViewModel(private val fetchFeedUseCase: FetchFeedUseCase) : ViewModel() {

    val viewState = MutableLiveData<FeedViewState>()

    fun loadFeedList() {
        viewModelScope.launch {

            fetchFeedUseCase.execute().let { posterListState ->
                when (posterListState) {
                    is PosterListState.LoadFail -> viewState.value =
                        FeedViewState.LoadFail

                    is PosterListState.Loaded -> viewState.value =
                        FeedViewState.Loaded(posterListState.posts)
                }
            }
        }
    }
}