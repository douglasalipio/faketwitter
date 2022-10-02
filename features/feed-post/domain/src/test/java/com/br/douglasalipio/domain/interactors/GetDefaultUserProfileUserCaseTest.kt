package com.br.douglasalipio.domain.interactors

import com.br.douglasalipio.domain.PosterRepository
import com.br.douglasalipio.domain.entities.UserProfile
import com.br.douglasalipio.domain.states.UserProfileState
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class GetDefaultUserProfileUserCaseTest {

    @MockK
    private lateinit var mockRepository: PosterRepository

    private lateinit var getDefaultUserProfile: GetDefaultUserProfileUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        getDefaultUserProfile = GetDefaultUserProfileUseCase(mockRepository)
    }


    @Test
    fun `WHEN get default user profile is called THEN return default user`() = runTest {
        //given
        val user = UserProfile(1, "username", "joinedDate", 0)
        coEvery { mockRepository.getDefaultUserProfile() } returns user
        //when
        val actualUserProfileState = getDefaultUserProfile.execute()
        assertEquals(UserProfileState.Loaded(user), actualUserProfileState)
    }
}