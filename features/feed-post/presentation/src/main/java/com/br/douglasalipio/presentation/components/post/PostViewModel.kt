package com.br.douglasalipio.presentation.components.post

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.br.douglasalipio.domain.entities.PostType
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
            getAllUserNamesUseCase.execute().let { postState ->
                when (postState) {
                    is GetAllUsernamesState.Loaded -> viewState.value =
                        PostViewState.UserLoaded(postState.usernames)

                    is GetAllUsernamesState.Fail -> viewState.value =
                        PostViewState.UserLoadFail
                }
            }
        }
    }

    fun requestPostContent(content: String, postType: PostType) {
        viewModelScope.launch {
            postUserCase.execute(PostUserCase.Params(content, postType))
        }
    }

}