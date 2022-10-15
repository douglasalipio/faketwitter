package com.br.douglasalipio.data

import com.br.douglasalipio.data.local.PosterFeedLocalStorage
import com.br.douglasalipio.data.local.models.TweetModel

class PosterFeedDataSourceImp(private val posterFeedLocalStorage: PosterFeedLocalStorage) : PosterFeedDataSource {

    override suspend fun getUserById(userId: Int) = posterFeedLocalStorage.getUserById(userId)

    override suspend fun getAllNames() = posterFeedLocalStorage.getAllNames()

    override suspend fun fetchFeed(): List<TweetModel> = posterFeedLocalStorage.fetchFeed()

    override suspend fun getDefaultUserProfile() = posterFeedLocalStorage.getDefaultUserProfile()

    override suspend fun getTotalUserPosts(userId: Int) =
        posterFeedLocalStorage.getTotalPostByUser(userId)

    override suspend fun postContent(tweetModel: TweetModel) =
        posterFeedLocalStorage.postContent(tweetModel)
}