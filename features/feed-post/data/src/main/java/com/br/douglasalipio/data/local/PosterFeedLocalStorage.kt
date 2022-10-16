package com.br.douglasalipio.data.local

import com.br.douglasalipio.data.local.models.PostModel
import com.br.douglasalipio.data.local.models.ProfileModel
import com.br.douglasalipio.domain.entities.PostType

class PosterFeedLocalStorage {

    private val posts = mutableListOf<PostModel>()
    private val users = mutableListOf<ProfileModel>()

    companion object {
        const val DEFAULT_USER_POST_SIZE = 1
    }

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
                PostModel(
                    content = "Hey folks, how are you?",
                    user = users[0],
                    postType = PostType.POST
                ), PostModel(
                    content = "Good morning everyone!",
                    user = users[1],
                    postType = PostType.POST
                ),
                PostModel(
                    content =
                    "I have no idea whats going on wit my computer lol",
                    user = users[2],
                    postType = PostType.POST
                ),
                PostModel(
                    content = "Happy new year!!!",
                    user = users[3],
                    postType = PostType.POST
                )
            )
        )


    }

    fun getAllNames() = users.map { it.username }

    fun getTotalCountNumbers(): List<Int> {
        return listOf(
            posts.filter { it.user.id == 0 && it.postType == PostType.POST }.size,
            posts.filter { it.user.id == 0 && it.postType == PostType.QUOTE_POST }.size,
            posts.filter { it.user.id == 0 && it.postType == PostType.RE_POSTING }.size
        )
    }

    fun getTotalPosts() = posts.filter { it.user.id == 0 }.size - DEFAULT_USER_POST_SIZE

    fun getUserById(userId: Int): ProfileModel {
        return users[userId]
    }

    fun fetchFeed(): List<PostModel> = posts

    fun getDefaultUserProfile() = users[0]

    fun postContent(postModel: PostModel): List<PostModel> {
        posts.add(postModel)
        return posts.toList()
    }
}