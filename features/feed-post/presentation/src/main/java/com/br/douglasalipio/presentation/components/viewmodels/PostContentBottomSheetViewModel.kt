package com.br.douglasalipio.presentation.components.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.br.douglasalipio.domain.interactors.GetAllUserNamesUseCase
import com.br.douglasalipio.domain.states.GetAllUsernamesState
import com.br.douglasalipio.presentation.components.states.PostContentBottomSheetState
import kotlinx.coroutines.launch

class PostContentBottomSheetViewModel(
    private val getAllUserNamesUseCase: GetAllUserNamesUseCase
) : ViewModel() {

    val viewState = MutableLiveData<PostContentBottomSheetState>()

    fun getAllUsernames() {
        viewModelScope.launch {

            getAllUserNamesUseCase.execute().let { getAllUsernamesState ->
                when (getAllUsernamesState) {
                    is GetAllUsernamesState.Loaded -> viewState.value =
                        PostContentBottomSheetState.UsernamesLoaded(getAllUsernamesState.usernames)

                    is GetAllUsernamesState.LoadFail -> viewState.value =
                        PostContentBottomSheetState.UsernamesLoadFail
                }
            }
        }
    }
}