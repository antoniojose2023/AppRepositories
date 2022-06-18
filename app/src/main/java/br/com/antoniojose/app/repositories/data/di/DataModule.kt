package br.com.antoniojose.app.repositories.data.di

import android.util.Log
import br.com.antoniojose.app.repositories.data.repository.Repository
import br.com.antoniojose.app.repositories.data.repository.RepositoryImp
import br.com.antoniojose.app.repositories.data.service.GitHubService

import com.google.gson.GsonBuilder

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.context.GlobalContext.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object DataModule {

    fun load(){
        loadKoinModules(networkModule() + repositoryModule())
    }

    private fun networkModule(): Module{
        return module{

            single {
                val interceptor = HttpLoggingInterceptor{
                    Log.i("tag", it)
                }

                interceptor.level = HttpLoggingInterceptor.Level.BODY

                OkHttpClient.Builder()
                    .addInterceptor( interceptor )
                    .build()
            }

            single {
                GsonConverterFactory.create(GsonBuilder().create())
            }

            single {
                createService<GitHubService>(get(), get())
            }

        }
    }

    private fun repositoryModule(): Module{
        return module {
            single<Repository> {RepositoryImp(get())}
        }
    }


    private inline fun <reified T> createService(okHttpCliente: OkHttpClient, factory: GsonConverterFactory): T{
            return Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .client( okHttpCliente )
                .addConverterFactory(factory)
                .build()
                .create(T::class.java)
    }

}