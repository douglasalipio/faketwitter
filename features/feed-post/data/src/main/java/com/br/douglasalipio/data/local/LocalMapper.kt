package com.br.douglasalipio.data.local

import com.br.douglasalipio.data.local.models.PostModel
import com.br.douglasalipio.data.local.models.UserProfileModel
import com.br.douglasalipio.domain.entities.Post
import com.br.douglasalipio.domain.entities.UserProfile

fun PostModel.mapToDomain() = Post(content = this.content, user = this.user.mapToDomain())

fun UserProfileModel.mapToDomain() = UserProfile(
    id = this.id,
    username = this.username,
    dateJoined = this.dateJoined,
    imageName = this.imageName
)