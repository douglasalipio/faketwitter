package com.br.douglasalipio.data

import com.br.douglasalipio.data.local.PosterLocalStorage
import com.br.douglasalipio.data.local.models.PostModel
import com.br.douglasalipio.data.local.models.UserProfileModel
import com.br.douglasalipio.domain.entities.Post
import com.br.douglasalipio.domain.entities.UserProfile

interface PosterDataSource {

    suspend fun getUserById(userId: Int): UserProfileModel

    suspend fun getAllNames(): List<String>

    suspend fun fetchFeed(): List<PostModel>

    suspend fun getDefaultUserProfile(): UserProfileModel

    suspend fun getTotalUserPosts(userId: Int) : Int
}