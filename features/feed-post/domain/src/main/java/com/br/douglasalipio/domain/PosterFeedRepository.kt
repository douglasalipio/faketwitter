package com.br.douglasalipio.domain

import com.br.douglasalipio.domain.entities.Post
import com.br.douglasalipio.domain.entities.Profile

interface PosterFeedRepository {

    suspend fun getUserById(userId: Int): Profile

    suspend fun getAllNames(): List<String>

    suspend fun fetchFeed(): List<Post>

    suspend fun getDefaultUserProfile(): Profile

    suspend fun getCountNumbers(): List<Int>

    suspend fun getTotalPosts(): Int

    suspend fun postContent(content: Post): List<Post>
}