package com.br.douglasalipio.data

import com.br.douglasalipio.data.local.PosterLocalStorage
import com.br.douglasalipio.data.local.models.PostModel

class PosterDataSourceImp(private val posterLocalStorage: PosterLocalStorage) : PosterDataSource {

    override suspend fun getUserById(userId: Int) = posterLocalStorage.getUserById(userId)

    override suspend fun getAllNames() = posterLocalStorage.getAllNames()

    override suspend fun fetchFeed(): List<PostModel> = posterLocalStorage.fetchFeed()

    override suspend fun getDefaultUserProfile() = posterLocalStorage.getDefaultUserProfile()

    override suspend fun getTotalUserPosts(userId: Int) =
        posterLocalStorage.getTotalPostByUser(userId)

    override suspend fun postContent(postModel: PostModel) =
        posterLocalStorage.postContent(postModel)
}