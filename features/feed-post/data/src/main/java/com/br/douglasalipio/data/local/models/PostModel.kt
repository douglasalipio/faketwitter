package com.br.douglasalipio.data.local.models

import com.br.douglasalipio.domain.entities.PostType

data class PostModel(val content: String, val user: ProfileModel, val postType: PostType)
