package com.br.douglasalipio.presentation.components.tweet

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.br.douglasalipio.domain.entities.UserProfile
import com.br.douglasalipio.domain.interactors.GetAllUserNamesUseCase
import com.br.douglasalipio.domain.interactors.GetDefaultUserProfileUseCase
import com.br.douglasalipio.domain.interactors.PostContentUserCase
import com.br.douglasalipio.domain.states.GetAllUsernamesState
import com.br.douglasalipio.domain.states.UserProfileState
import kotlinx.coroutines.launch

class TwitteViewModel(
    private val getDefaultUserProfileUseCase: GetDefaultUserProfileUseCase,
    private val getAllUserNamesUseCase: GetAllUserNamesUseCase,
    private val postContentUserCase: PostContentUserCase
) : ViewModel() {

    val viewState = MutableLiveData<TweetViewState>()

    fun getAllUsernames() {
        viewModelScope.launch {
            getAllUserNamesUseCase.execute().let { getAllUsernamesState ->
                when (getAllUsernamesState) {
                    is GetAllUsernamesState.Loaded -> viewState.value =
                        TweetViewState.UsernamesLoaded(getAllUsernamesState.usernames)

                    is GetAllUsernamesState.LoadFail -> viewState.value =
                        TweetViewState.UsernamesLoadFail
                }
            }
        }
    }

    fun requestPostContent(content: String) {
        viewModelScope.launch {
            getDefaultUserProfileUseCase.execute().let { userState ->
                when (userState) {
                    is UserProfileState.Loaded -> postContent(content, userState.defaultUser)
                    else -> {}
                }
            }
        }
    }

    private fun postContent(content: String, userProfile: UserProfile) {
        viewModelScope.launch {
            postContentUserCase.execute(PostContentUserCase.Params(userProfile, content))
        }
    }

}