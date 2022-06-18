package br.com.antoniojose.app.repositories.domain

import br.com.antoniojose.app.repositories.core.UseCase
import br.com.antoniojose.app.repositories.data.model.Repo
import br.com.antoniojose.app.repositories.data.repository.Repository
import kotlinx.coroutines.flow.Flow

class ListRepositoryUseCases(private val repository: Repository): UseCase<String, Flow<List<Repo>>>() {
    override suspend fun execute(param: String): Flow<List<Repo>> {
         return repository.listRepositories(param)
    }
}