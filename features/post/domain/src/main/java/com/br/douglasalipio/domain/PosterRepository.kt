package com.br.douglasalipio.domain

import com.br.douglasalipio.domain.entities.Post

interface PosterRepository {

    suspend fun fetchTimeline(): List<Post>
}