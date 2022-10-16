package com.br.douglasalipio.data.local.mappers

import com.br.douglasalipio.data.local.models.PostModel
import com.br.douglasalipio.domain.entities.Post

fun PostModel.mapToDomain() =
    Post(content = this.content, user = this.user.mapToDomain(), this.postType)

fun Post.mapToData() = PostModel(this.content, this.user.mapToData(), this.postType)

