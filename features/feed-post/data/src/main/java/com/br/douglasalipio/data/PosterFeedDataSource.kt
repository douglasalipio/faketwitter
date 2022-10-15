package com.br.douglasalipio.data

import com.br.douglasalipio.data.local.models.TweetModel
import com.br.douglasalipio.data.local.models.ProfileModel

interface PosterFeedDataSource {

    suspend fun getUserById(userId: Int): ProfileModel

    suspend fun getAllNames(): List<String>

    suspend fun fetchFeed(): List<TweetModel>

    suspend fun getDefaultUserProfile(): ProfileModel

    suspend fun getTotalUserPosts(userId: Int) : Int

    suspend fun postContent(tweetModel: TweetModel): List<TweetModel>
}