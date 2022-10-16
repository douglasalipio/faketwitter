package com.br.douglasalipio.domain.entities

data class Post(
    val content: String,
    val user: Profile,
    val postType: PostType
)