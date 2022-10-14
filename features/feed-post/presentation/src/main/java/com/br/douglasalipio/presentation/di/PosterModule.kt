package com.br.douglasalipio.presentation.di

import com.br.douglasalipio.data.PosterDataSource
import com.br.douglasalipio.data.PosterDataSourceImp
import com.br.douglasalipio.data.PosterRepositoryImp
import com.br.douglasalipio.data.local.PosterLocalStorage
import com.br.douglasalipio.domain.PosterRepository
import com.br.douglasalipio.domain.interactors.FetchFeedUseCase
import com.br.douglasalipio.domain.interactors.GetAllUserNamesUseCase
import com.br.douglasalipio.domain.interactors.GetDefaultUserProfileUseCase
import com.br.douglasalipio.domain.interactors.GetTotalUserPostsUseCase
import com.br.douglasalipio.presentation.components.viewmodels.PostContentBottomSheetViewModel
import com.br.douglasalipio.presentation.components.viewmodels.PosterViewModel
import com.br.douglasalipio.presentation.components.viewmodels.UserProfileViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object PosterModule {
    val presentationModule = module {
        viewModel { PosterViewModel(get()) }
        viewModel { PostContentBottomSheetViewModel(get()) }
        viewModel { UserProfileViewModel(get(), get()) }
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
    }
}