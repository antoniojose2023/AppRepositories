package br.com.antoniojose.app.repositories.domain.di

import br.com.antoniojose.app.repositories.domain.ListRepositoryUseCases
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module

object DomainModule {

    fun load(){
        loadKoinModules(useCaseModule())
    }

    private fun useCaseModule(): Module{
        return module {
                factory {
                    ListRepositoryUseCases(get())
                }
        }
    }

}