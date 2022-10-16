package com.br.douglasalipio.data.local.mappers

import com.br.douglasalipio.data.local.models.TweetModel
import com.br.douglasalipio.domain.entities.Tweet

fun TweetModel.mapToDomain() =
    Tweet(content = this.content, user = this.user.mapToDomain(), this.hasRepost)

fun Tweet.mapToData() = TweetModel(this.content, this.user.mapToData(), this.hasRepost)

