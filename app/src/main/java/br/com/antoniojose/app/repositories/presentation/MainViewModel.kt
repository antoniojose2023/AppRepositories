package br.com.antoniojose.app.repositories.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.antoniojose.app.repositories.data.model.Repo
import br.com.antoniojose.app.repositories.domain.ListRepositoryUseCases
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class MainViewModel(private val listRepositoryUseCases: ListRepositoryUseCases): ViewModel() {

    private var _repo: MutableLiveData<State> = MutableLiveData<State>()
    val repo: LiveData<State> = _repo

    fun listRepositories(usuario: String){
        viewModelScope.launch {
             listRepositoryUseCases(usuario)
                 .onStart {
                     _repo.value = State.Loading
                  }.catch {
                     _repo.value = State.Erro("Erro ao listar os repositórios.")
                 }.collect {
                     _repo.value = State.Sucess(it)
                 }
        }
    }

    sealed class State{
        object Loading: State()
        open class Erro(val message: String): State()
        class Sucess(val listRepositories: List<Repo>): State()
    }

}