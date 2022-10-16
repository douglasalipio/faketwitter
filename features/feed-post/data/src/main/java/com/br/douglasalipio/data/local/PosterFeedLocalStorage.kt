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
                    content = "Hey folks, how are you?",
                    user = users[0],
                    hasRepost = false
                ), TweetModel(
                    content = "Good morning everyone!",
                    user = users[1],
                    hasRepost = false
                ),
                TweetModel(
                    content =
                    "I have no idea whats going on wit my computer lol",
                    user = users[2],
                    hasRepost = false
                ),
                TweetModel(
                    content = "Happy new year!!!",
                    user = users[3],
                    hasRepost = false
                )
            )
        )


    }

    fun getAllNames() = users.map { it.username }

    fun getTotalPostByUser(userId: Int) = posts.filter { it.user.id == userId }.size

    fun getUserById(userId: Int): ProfileModel {
        return users[userId]
    }

    fun fetchFeed(): List<TweetModel> = posts

    fun getDefaultUserProfile() = users[0]

    fun postContent(tweetModel: TweetModel): List<TweetModel> {
        posts.add(tweetModel)
        return posts.toList()
    }
}