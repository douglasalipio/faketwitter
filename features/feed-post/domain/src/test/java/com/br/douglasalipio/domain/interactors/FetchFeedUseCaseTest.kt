package com.br.douglasalipio.domain.interactors

import com.br.douglasalipio.domain.PosterFeedRepository
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
    private lateinit var mockRepository: PosterFeedRepository

    private lateinit var fetchFeedListUseCase: FetchFeedListUseCase

    @Before
    fun setUp() {
         MockKAnnotations.init(this)
        fetchFeedListUseCase = FetchFeedListUseCase(mockRepository)
    }


    @Test
    fun `WHEN fetch feed use case is called THEN return post list`() = runBlockingTest{
        //given
        val tweets = listOf<Post>()
        coEvery { mockRepository.fetchFeed() } returns tweets
        //when
        val actualPostResult = fetchFeedListUseCase.execute()
        Assert.assertEquals(tweets, actualPostResult)
    }
}