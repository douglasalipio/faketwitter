package com.br.douglasalipio.data

import com.br.douglasalipio.data.local.models.TweetModel
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
    private lateinit var dataSource: PosterFeedDataSource
    private lateinit var repository: PosterFeedRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        repository = PosterFeedRepositoryImp(dataSource)
    }

    @Test
    fun `WHEN fetch feed list is called THEN returns post list`() {
        //given
        val posts = listOf<TweetModel>()
        coEvery { dataSource.fetchFeed() } returns posts
        //when
        val domainPosts = runTest { repository.fetchFeed() }
        //then
        coVerify { dataSource.fetchFeed() }
    }

    @Test
    fun `GIVEN user id WHEN get user is called THEN returns user`() = runTest {
        //given
        val userProfile = ProfileModel(1, "username", "April 25, 2021", "local_image")
        coEvery { dataSource.getUserById(1) } returns userProfile
        //when
        val domainUser = repository.getUserById(1)
        //then
        coVerify { dataSource.getUserById(1) }
    }

    @Test
    fun `WHEN get default user is called THEN returns user`() = runTest {
        //given
        val userProfile = ProfileModel(1, "username", "April 25, 2021", "local_image")
        coEvery { dataSource.getDefaultUserProfile() } returns userProfile
        //when
        val domainUser = repository.getDefaultUserProfile()
        //then
        coVerify { dataSource.getDefaultUserProfile() }
    }
}