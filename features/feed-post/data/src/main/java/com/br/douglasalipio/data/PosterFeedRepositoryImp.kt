package com.br.douglasalipio.data

import com.br.douglasalipio.data.local.mapToData
import com.br.douglasalipio.data.local.mapToDomain
import com.br.douglasalipio.domain.PosterFeedRepository
import com.br.douglasalipio.domain.entities.Tweet

class PosterFeedRepositoryImp(private val dataSource: PosterFeedDataSource) : PosterFeedRepository {

    override suspend fun getUserById(userId: Int) = dataSource.getUserById(userId).mapToDomain()

    override suspend fun getAllNames() = dataSource.getAllNames()

    override suspend fun fetchFeed() = dataSource.fetchFeed().map { it.mapToDomain() }

    override suspend fun getDefaultUserProfile() = dataSource.getDefaultUserProfile().mapToDomain()

    override suspend fun getTotalUserPosts(userId: Int) = dataSource.getTotalUserPosts(userId)

    override suspend fun postContent(tweet: Tweet): List<Tweet> {
        val newPostList = dataSource.postContent(tweet.mapToData())
        return newPostList.map { it.mapToDomain() }
    }

}