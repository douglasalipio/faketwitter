package com.br.douglasalipio.domain

import com.br.douglasalipio.domain.entities.Post
import com.br.douglasalipio.domain.entities.UserProfile

interface PosterRepository {

    suspend fun getUserById(userId: Int): UserProfile

    suspend fun getAllNames(): List<String>

    suspend fun fetchFeed(): List<Post>

    suspend fun getDefaultUserProfile(): UserProfile

    suspend fun getTotalUserPosts(userId: Int): Int

    suspend fun postContent(post: Post): List<Post>

}