package com.br.douglasalipio.presentation.components.tweet

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.br.douglasalipio.domain.entities.Profile
import com.br.douglasalipio.domain.interactors.GetAllUserNamesUseCase
import com.br.douglasalipio.domain.interactors.GetDefaultProfileUseCase
import com.br.douglasalipio.domain.interactors.TweetContentUserCase
import com.br.douglasalipio.domain.states.GetAllUsernamesState
import com.br.douglasalipio.domain.states.ProfileState
import kotlinx.coroutines.launch

class TweetViewModel(
    private val getDefaultProfileUseCase: GetDefaultProfileUseCase,
    private val getAllUserNamesUseCase: GetAllUserNamesUseCase,
    private val tweetContentUserCase: TweetContentUserCase
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
            getDefaultProfileUseCase.execute().let { userState ->
                when (userState) {
                    is ProfileState.Loaded -> postContent(content, userState.defaultUser)
                    else -> {}
                }
            }
        }
    }

    private fun postContent(content: String, profile: Profile) {
        viewModelScope.launch {
            tweetContentUserCase.execute(TweetContentUserCase.Params(profile, content))
        }
    }

}