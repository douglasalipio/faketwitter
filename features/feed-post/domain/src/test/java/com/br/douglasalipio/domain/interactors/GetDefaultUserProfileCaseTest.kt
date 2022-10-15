package com.br.douglasalipio.domain.interactors

import com.br.douglasalipio.domain.PosterFeedRepository
import com.br.douglasalipio.domain.entities.Profile
import com.br.douglasalipio.domain.states.ProfileState
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class GetDefaultUserProfileCaseTest {

    @MockK
    private lateinit var mockRepository: PosterFeedRepository

    private lateinit var getDefaultUserProfile: GetDefaultProfileUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        getDefaultUserProfile = GetDefaultProfileUseCase(mockRepository)
    }


    @Test
    fun `WHEN get default user profile is called THEN return default user`() = runTest {
        //given
        val user = Profile(1, "username", "joinedDate", "imageName")
        coEvery { mockRepository.getDefaultUserProfile() } returns user
        //when
        val actualUserProfileState = getDefaultUserProfile.execute()
        assertEquals(ProfileState.Loaded(user), actualUserProfileState)
    }
}