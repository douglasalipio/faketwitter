package com.br.douglasalipio.presentation.di

import com.br.douglasalipio.data.PosterFeedDataSource
import com.br.douglasalipio.data.PosterFeedDataSourceImp
import com.br.douglasalipio.data.PosterFeedRepositoryImp
import com.br.douglasalipio.data.local.PosterFeedLocalStorage
import com.br.douglasalipio.domain.PosterFeedRepository
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
        single<PosterFeedDataSource> { PosterFeedDataSourceImp(PosterFeedLocalStorage()) }
        single<PosterFeedRepository> { PosterFeedRepositoryImp(get()) }
    }
    val domainModule = module {
        factory { FetchFeedListUseCase(get()) }
        factory { GetDefaultProfileUseCase(get()) }
        factory { GetTotalUserTweetsUseCase(get()) }
        factory { GetAllUserNamesUseCase(get()) }
        factory { TweetContentUserCase(get()) }
    }
}