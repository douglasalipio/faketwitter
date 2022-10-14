package com.br.douglasalipio.presentation.components.states

sealed class PostContentBottomSheetState {

    object UsernamesLoadFail : PostContentBottomSheetState()
    data class UsernamesLoaded(val usernames: List<String>) : PostContentBottomSheetState()
}