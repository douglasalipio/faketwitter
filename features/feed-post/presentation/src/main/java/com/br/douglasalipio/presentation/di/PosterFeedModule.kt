package com.br.douglasalipio.presentation.di

import com.br.douglasalipio.data.PosterDataSource
import com.br.douglasalipio.data.PosterDataSourceImp
import com.br.douglasalipio.data.PosterRepositoryImp
import com.br.douglasalipio.data.local.PosterLocalStorage
import com.br.douglasalipio.domain.PosterRepository
import com.br.douglasalipio.domain.interactors.*
import com.br.douglasalipio.presentation.components.tweet.TwitteViewModel
import com.br.douglasalipio.presentation.components.feed.FeedViewModel
import com.br.douglasalipio.presentation.components.profile.ProfileViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object PosterFeedModule {
    val presentationModule = module {
        viewModel { FeedViewModel(get()) }
        viewModel { TwitteViewModel(get(), get(), get()) }
        viewModel { ProfileViewModel(get(), get()) }
    }
    val dataModule = module {
        single<PosterDataSource> { PosterDataSourceImp(PosterLocalStorage()) }
        single<PosterRepository> { PosterRepositoryImp(get()) }
    }
    val domainModule = module {
        factory { FetchFeedUseCase(get()) }
        factory { GetDefaultUserProfileUseCase(get()) }
        factory { GetTotalUserPostsUseCase(get()) }
        factory { GetAllUserNamesUseCase(get()) }
        factory { PostContentUserCase(get()) }
    }
}