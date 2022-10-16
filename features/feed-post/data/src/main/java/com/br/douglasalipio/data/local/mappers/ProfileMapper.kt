package com.br.douglasalipio.data.local.mappers

import com.br.douglasalipio.data.local.models.ProfileModel
import com.br.douglasalipio.domain.entities.Profile

fun Profile.mapToData() =
    ProfileModel(this.id, this.username, this.dateJoined, this.imageName)

fun ProfileModel.mapToDomain() = Profile(
    id = this.id,
    username = this.username,
    dateJoined = this.dateJoined,
    imageName = this.imageName
)