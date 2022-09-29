package com.br.douglasalipio.presentation

import android.app.Application
import com.br.douglasalipio.presentation.di.PosterModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class PosterApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin()
    }

    private fun startKoin() {
        startKoin {
            androidLogger(level = Level.ERROR)
            androidContext(this@PosterApplication)
            modules(
                PosterModule.dataModule,
                PosterModule.domainModule,
                PosterModule.presentationModule
            )

        }
    }
}