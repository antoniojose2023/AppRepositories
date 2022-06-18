package br.com.antoniojose.app.repositories

import android.app.Application
import br.com.antoniojose.app.repositories.data.di.DataModule
import br.com.antoniojose.app.repositories.domain.di.DomainModule
import br.com.antoniojose.app.repositories.presentation.di.ViewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.dsl.module

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
        }

        DataModule.load()
        DomainModule.load()
        ViewModelModule.load()
    }
}