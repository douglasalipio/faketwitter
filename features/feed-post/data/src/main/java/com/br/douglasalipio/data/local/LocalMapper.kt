package com.br.douglasalipio.data.local

import com.br.douglasalipio.data.local.models.TweetModel
import com.br.douglasalipio.data.local.models.ProfileModel
import com.br.douglasalipio.domain.entities.Tweet
import com.br.douglasalipio.domain.entities.Profile

fun TweetModel.mapToDomain() = Tweet(content = this.content, user = this.user.mapToDomain())
fun Tweet.mapToData() = TweetModel(this.content, this.user.mapToData())
fun Profile.mapToData() =
    ProfileModel(this.id, this.username, this.dateJoined, this.imageName)

fun ProfileModel.mapToDomain() = Profile(
    id = this.id,
    username = this.username,
    dateJoined = this.dateJoined,
    imageName = this.imageName
)