package com.br.douglasalipio.data

import com.br.douglasalipio.data.local.mapToData
import com.br.douglasalipio.data.local.mapToDomain
import com.br.douglasalipio.data.local.models.PostModel
import com.br.douglasalipio.domain.PosterRepository
import com.br.douglasalipio.domain.entities.Post
import com.br.douglasalipio.domain.entities.UserProfile

class PosterRepositoryImp(private val dataSource: PosterDataSource) : PosterRepository {

    override suspend fun getUserById(userId: Int) = dataSource.getUserById(userId).mapToDomain()

    override suspend fun getAllNames() = dataSource.getAllNames()

    override suspend fun fetchFeed() = dataSource.fetchFeed().map { it.mapToDomain() }

    override suspend fun getDefaultUserProfile() = dataSource.getDefaultUserProfile().mapToDomain()

    override suspend fun getTotalUserPosts(userId: Int) = dataSource.getTotalUserPosts(userId)

    override suspend fun postContent(post: Post): List<Post> {
        val newPostList = dataSource.postContent(post.mapToData())
        return newPostList.map { it.mapToDomain() }
    }

}