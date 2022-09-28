package com.br.douglasalipio.domain.interactors

import com.br.douglasalipio.domain.PosterRepository
import com.br.douglasalipio.domain.entities.Post
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class FetchFeedUseCaseTest {

    @MockK
    private lateinit var mockRepository: PosterRepository

    private lateinit var fetchFeedUseCase: FetchFeedUseCase

    @Before
    fun setUp() {
         MockKAnnotations.init(this)
        fetchFeedUseCase = FetchFeedUseCase(mockRepository)
    }


    @Test
    fun `WHEN fetch feed use case is called THEN return post list`() = runBlockingTest{
        //given
        val posts = listOf<Post>()
        coEvery { mockRepository.fetchFeed() } returns posts
        //when
        val actualPostResult = fetchFeedUseCase.execute()
        Assert.assertEquals(posts, actualPostResult)
    }
}