package com.br.douglasalipio.data

import com.br.douglasalipio.data.local.models.PostModel
import com.br.douglasalipio.data.local.models.UserProfileModel
import com.br.douglasalipio.domain.PosterRepository
import com.br.douglasalipio.domain.entities.Post
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class PosterRepositoryTest {

    @MockK
    private lateinit var dataSource: PosterDataSource
    private lateinit var repository: PosterRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        repository = PosterRepositoryImp(dataSource)
    }

    @Test
    fun `WHEN fetch feed list is called THEN returns post list`() {
        //given
        val posts = listOf<PostModel>()
        coEvery { dataSource.fetchFeed() } returns posts
        //when
        val domainPosts = runTest { repository.fetchFeed() }
        //then
        coVerify { dataSource.fetchFeed() }
    }

    @Test
    fun `GIVEN user id WHEN get user is called THEN returns user`() = runTest {
        //given
        val userProfile = UserProfileModel(1, "username", "April 25, 2021", 1)
        coEvery { dataSource.getUserById(1) } returns userProfile
        //when
        val domainUser = repository.getUserById(1)
        //then
        coVerify { dataSource.getUserById(1) }
    }

    @Test
    fun `WHEN get default user is called THEN returns user`() = runTest {
        //given
        val userProfile = UserProfileModel(1, "username", "April 25, 2021", 1)
        coEvery { dataSource.getDefaultUserProfile() } returns userProfile
        //when
        val domainUser = repository.getDefaultUserProfile()
        //then
        coVerify { dataSource.getDefaultUserProfile() }
    }
}