package br.com.antoniojose.app.repositories.data.service

import br.com.antoniojose.app.repositories.data.model.Repo
import kotlinx.coroutines.flow.Flow
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface GitHubService {

    @GET("users/{user}/repos")
    suspend fun listRepositories(@Path("user") user: String): List<Repo>
}