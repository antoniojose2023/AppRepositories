package br.com.antoniojose.app.repositories.data.repository


import br.com.antoniojose.app.repositories.data.service.GitHubService
import kotlinx.coroutines.flow.flow

class RepositoryImp(private val service: GitHubService): Repository {
    override suspend fun listRepositories(usuario: String) = flow{
            val seviceGitHub = service.listRepositories( usuario )
            emit(seviceGitHub)
    }
}