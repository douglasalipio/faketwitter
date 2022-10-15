package com.br.douglasalipio.domain.states

import com.br.douglasalipio.domain.entities.Tweet

sealed class PosterListState {

    object LoadFail : PosterListState()

    data class Loaded(val tweets: List<Tweet>) : PosterListState()
}