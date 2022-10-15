package com.br.douglasalipio.data

import com.br.douglasalipio.data.local.models.PostModel
import com.br.douglasalipio.data.local.models.UserProfileModel

interface PosterDataSource {

    suspend fun getUserById(userId: Int): UserProfileModel

    suspend fun getAllNames(): List<String>

    suspend fun fetchFeed(): List<PostModel>

    suspend fun getDefaultUserProfile(): UserProfileModel

    suspend fun getTotalUserPosts(userId: Int) : Int

    suspend fun postContent(postModel: PostModel): List<PostModel>
}