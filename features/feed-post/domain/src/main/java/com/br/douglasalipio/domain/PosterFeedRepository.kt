package com.br.douglasalipio.domain

import com.br.douglasalipio.domain.entities.Tweet
import com.br.douglasalipio.domain.entities.Profile

interface PosterFeedRepository {

    suspend fun getUserById(userId: Int): Profile

    suspend fun getAllNames(): List<String>

    suspend fun fetchFeed(): List<Tweet>

    suspend fun getDefaultUserProfile(): Profile

    suspend fun getTotalUserPosts(userId: Int): Int

    suspend fun postContent(tweet: Tweet): List<Tweet>

}