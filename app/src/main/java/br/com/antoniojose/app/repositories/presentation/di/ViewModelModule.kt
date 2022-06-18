package br.com.antoniojose.app.repositories.presentation.di

import br.com.antoniojose.app.repositories.presentation.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module

object ViewModelModule {

    fun load(){
         loadKoinModules(mainViewModel())
    }

    private fun mainViewModel(): Module{
        return module {
            viewModel {
                MainViewModel(get())
            }
        }
    }
}