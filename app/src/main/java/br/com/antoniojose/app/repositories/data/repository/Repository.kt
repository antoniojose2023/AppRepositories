package br.com.antoniojose.app.repositories.data.repository

import br.com.antoniojose.app.repositories.data.model.Repo
import kotlinx.coroutines.flow.Flow
import retrofit2.Call


interface Repository {
    suspend fun listRepositories(usuario: String): Flow<List<Repo>>
}