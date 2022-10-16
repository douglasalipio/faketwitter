package com.br.douglasalipio.data

import com.br.douglasalipio.data.local.PosterFeedLocalStorage
import com.br.douglasalipio.data.local.models.PostModel
import com.br.douglasalipio.data.local.models.ProfileModel
import com.br.douglasalipio.domain.PosterFeedRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class PosterFeedRepositoryTest {

    @MockK
    private lateinit var localStorage: PosterFeedLocalStorage
    private lateinit var repository: PosterFeedRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        repository = PosterFeedRepositoryImp(localStorage)
    }

    @Test
    fun `WHEN fetch feed list is called THEN returns post list`() {
        //given
        val posts = listOf<PostModel>()
        coEvery { localStorage.fetchFeed() } returns posts
        //when
        val domainPosts = runTest { repository.fetchFeed() }
        //then
        coVerify { localStorage.fetchFeed() }
    }

    @Test
    fun `GIVEN user id WHEN get user is called THEN returns user`() = runTest {
        //given
        val userProfile = ProfileModel(1, "username", "April 25, 2021", "local_image")
        coEvery { localStorage.getUserById(1) } returns userProfile
        //when
        val domainUser = repository.getUserById(1)
        //then
        coVerify { localStorage.getUserById(1) }
    }

    @Test
    fun `WHEN get default user is called THEN returns user`() = runTest {
        //given
        val userProfile = ProfileModel(1, "username", "April 25, 2021", "local_image")
        coEvery { localStorage.getDefaultUserProfile() } returns userProfile
        //when
        val domainUser = repository.getDefaultUserProfile()
        //then
        coVerify { localStorage.getDefaultUserProfile() }
    }
}