package com.br.douglasalipio.presentation.components.post

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.br.douglasalipio.domain.interactors.GetAllUserNamesUseCase
import com.br.douglasalipio.domain.interactors.PostUserCase
import com.br.douglasalipio.domain.states.GetAllUsernamesState
import kotlinx.coroutines.launch

class PostViewModel(
    private val getAllUserNamesUseCase: GetAllUserNamesUseCase,
    private val postUserCase: PostUserCase
) : ViewModel() {

    val viewState = MutableLiveData<PostViewState>()

    fun getAllUsernames() {
        viewModelScope.launch {
            getAllUserNamesUseCase.execute().let { getAllUsernamesState ->
                when (getAllUsernamesState) {
                    is GetAllUsernamesState.Loaded -> viewState.value =
                        PostViewState.UsernamesLoaded(getAllUsernamesState.usernames)

                    is GetAllUsernamesState.Fail -> viewState.value =
                        PostViewState.UsernamesLoadFail
                }
            }
        }
    }

    fun requestPostContent(content: String, hasRepost: Boolean, itemPosition: Int) {
        viewModelScope.launch {
            postUserCase.execute(PostUserCase.Params(content, hasRepost, itemPosition))
        }
    }

}