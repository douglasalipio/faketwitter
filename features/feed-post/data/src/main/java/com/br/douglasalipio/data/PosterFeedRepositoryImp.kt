package com.br.douglasalipio.data

import com.br.douglasalipio.data.local.PosterFeedLocalStorage
import com.br.douglasalipio.data.local.mappers.mapToData
import com.br.douglasalipio.data.local.mappers.mapToDomain
import com.br.douglasalipio.domain.PosterFeedRepository
import com.br.douglasalipio.domain.entities.Post

class PosterFeedRepositoryImp(private val localRepository: PosterFeedLocalStorage) :
    PosterFeedRepository {

    override suspend fun getUserById(userId: Int) =
        localRepository.getUserById(userId).mapToDomain()

    override suspend fun getAllNames() = localRepository.getAllNames()

    override suspend fun fetchFeed() = localRepository.fetchFeed().map { it.mapToDomain() }

    override suspend fun getDefaultUserProfile() =
        localRepository.getDefaultUserProfile().mapToDomain()

    override suspend fun getCountNumbers() = localRepository.getTotalCountNumbers()

    override suspend fun getTotalPosts() = localRepository.getTotalPosts()

    override suspend fun postContent(content: Post): List<Post> {
        val newPostList = localRepository.postContent(content.mapToData())
        return newPostList.map { it.mapToDomain() }
    }
}