package com.br.douglasalipio.data

import com.br.douglasalipio.data.local.PosterLocalStorage
import com.br.douglasalipio.data.local.models.PostModel
import com.br.douglasalipio.domain.entities.Post
import com.br.douglasalipio.domain.entities.UserProfile

class PosterDataSourceImp(private val posterLocalStorage: PosterLocalStorage) : PosterDataSource {

    override suspend fun getUserById(userId: Int) = posterLocalStorage.getUserById(userId)

    override suspend fun fetchFeed(): List<PostModel> = posterLocalStorage.fetchFeed()

    override suspend fun getDefaultUserProfile() = posterLocalStorage.getDefaultUserProfile()

    override suspend fun getTotalUserPosts(userId: Int) =
        posterLocalStorage.getTotalPostByUser(userId)

}