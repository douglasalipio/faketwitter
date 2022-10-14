package com.br.douglasalipio.data.local

import com.br.douglasalipio.data.local.models.PostModel
import com.br.douglasalipio.data.local.models.UserProfileModel
import com.br.douglasalipio.domain.entities.Post
import com.br.douglasalipio.domain.entities.UserProfile

class PosterLocalStorage {

    private val posts = mutableListOf<PostModel>()
    private val users = mutableListOf<UserProfileModel>()

    init {
        users.addAll(
            listOf(
                UserProfileModel(
                    id = 0,
                    username = "@d.mesquita",
                    dateJoined = "March 25, 2021",
                    imageName = "mouse.jpeg"
                ),
                UserProfileModel(
                    id = 1,
                    username = "@melhortimesanta",
                    dateJoined = "March 25, 2021",
                    imageName = "cool_monkey.png"
                ),
                UserProfileModel(
                    id = 2,
                    username = "@cat4sale",
                    dateJoined = "March 01, 2021",
                    imageName = "piu_piu.png"
                ), UserProfileModel(
                    id = 3,
                    username = "@josesilva",
                    dateJoined = "April 25, 2021",
                    imageName = "hal_9000.jpeg"
                )
            )
        )
        posts.addAll(
            listOf(
                PostModel(
                    content = "In publishing and graphic design, Lorem ipsum is a placeholder text commonly used to demonstrate the visual",
                    user = users[0]
                ), PostModel(
                    content = "Graphic design, Lorem ipsum is a placeholder text commonly used to demonstrate the visual",
                    user = users[1]
                ),
                PostModel(
                    content =
                    "In publishing and graphic design, Lorem ipsum is a placeholder text commonly used to demonstrate the visual",
                    user = users[2]
                ),
                PostModel(
                    content = "In publishing and graphic design, Lorem ipsum is a placeholder text",
                    user = users[3]
                )
            )
        )

    }

    fun getAllNames() = users.map { it.username }

    fun getTotalPostByUser(userId: Int) = users.filter { it.id == userId }.size

    fun getUserById(userId: Int) = users[userId]

    fun fetchFeed(): List<PostModel> = posts

    fun getDefaultUserProfile() = users[0]

}