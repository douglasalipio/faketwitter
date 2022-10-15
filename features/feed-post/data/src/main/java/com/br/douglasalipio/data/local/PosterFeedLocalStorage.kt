package com.br.douglasalipio.data.local

import com.br.douglasalipio.data.local.models.TweetModel
import com.br.douglasalipio.data.local.models.ProfileModel

class PosterFeedLocalStorage {

    private val posts = mutableListOf<TweetModel>()
    private val users = mutableListOf<ProfileModel>()

    init {
        users.addAll(
            listOf(
                ProfileModel(
                    id = 0,
                    username = "@d.mesquita",
                    dateJoined = "March 25, 2021",
                    imageName = "mouse.jpeg"
                ),
                ProfileModel(
                    id = 1,
                    username = "@melhortimesanta",
                    dateJoined = "March 25, 2021",
                    imageName = "cool_monkey.png"
                ),
                ProfileModel(
                    id = 2,
                    username = "@cat4sale",
                    dateJoined = "March 01, 2021",
                    imageName = "piu_piu.png"
                ), ProfileModel(
                    id = 3,
                    username = "@josesilva",
                    dateJoined = "April 25, 2021",
                    imageName = "hal_9000.jpeg"
                )
            )
        )
        posts.addAll(
            listOf(
                TweetModel(
                    content = "In publishing and graphic design, Lorem ipsum is a placeholder text commonly used to demonstrate the visual",
                    user = users[0]
                ), TweetModel(
                    content = "Graphic design, Lorem ipsum is a placeholder text commonly used to demonstrate the visual",
                    user = users[1]
                ),
                TweetModel(
                    content =
                    "In publishing and graphic design, Lorem ipsum is a placeholder text commonly used to demonstrate the visual",
                    user = users[2]
                ),
                TweetModel(
                    content = "In publishing and graphic design, Lorem ipsum is a placeholder text",
                    user = users[3]
                )
            )
        )


    }

    fun getAllNames() = users.map { it.username }

    fun getTotalPostByUser(userId: Int) = posts.filter { it.user.id == userId }.size

    fun getUserById(userId: Int) = users[userId]

    fun fetchFeed(): List<TweetModel> = posts

    fun getDefaultUserProfile() = users[0]

    fun postContent(tweetModel: TweetModel): List<TweetModel> {
        posts.add(tweetModel)
        return posts.toList()
    }
}