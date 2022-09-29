package com.br.douglasalipio.presentation.components.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.br.douglasalipio.domain.interactors.FetchFeedUseCase
import com.br.douglasalipio.domain.states.PosterListState
import com.br.douglasalipio.presentation.components.PosterListFragmentState
import kotlinx.coroutines.launch

class PosterViewModel(private val fetchFeedUseCase: FetchFeedUseCase) : ViewModel() {

    val viewState = MutableLiveData<PosterListFragmentState>()

    fun loadFeedList() {
        viewModelScope.launch {

            fetchFeedUseCase.execute().let { posterListState ->
                when (posterListState) {
                    is PosterListState.LoadFail -> viewState.value =
                        PosterListFragmentState.LoadFail

                    is PosterListState.Loaded -> viewState.value =
                        PosterListFragmentState.Loaded(posterListState.posts)
                }
            }
        }
    }
}