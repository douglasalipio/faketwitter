package com.br.douglasalipio.domain.entities

data class UserProfile(
    val id: Int,
    val username: String,
    val dateJoined: String,
    val totalPost: Int
)