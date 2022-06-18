package br.com.antoniojose.app.repositories.ui

import android.os.Bundle
import android.util.Log
import android.view.Menu

import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView

import br.com.antoniojose.app.repositories.R
import br.com.antoniojose.app.repositories.core.createDialog
import br.com.antoniojose.app.repositories.core.createProgressDialog
import br.com.antoniojose.app.repositories.core.hideSoftKeyboard
import br.com.antoniojose.app.repositories.databinding.ActivityMainBinding
import br.com.antoniojose.app.repositories.presentation.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity(), SearchView.OnQueryTextListener {

    private val mainViewModel by viewModel<MainViewModel>()
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val adapter by lazy { AdapterRepo() }
    private val dialogProgress by lazy {  createProgressDialog()  }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        binding.rvRepo.adapter = adapter

        mainViewModel.repo.observe(this){
                when(it){
                    MainViewModel.State.Loading -> {
                        dialogProgress.show()
                    }
                    is MainViewModel.State.Erro -> {
                        dialogProgress.dismiss()
                        createDialog {
                            setMessage( it.message )
                        }.show()
                    }
                    is MainViewModel.State.Sucess -> {
                        adapter.submitList( it.listRepositories )
                        dialogProgress.dismiss()
                    }
                }
        }

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        val searchView = menu.findItem(R.id.action_search)?.actionView as SearchView
        searchView.setOnQueryTextListener(this)

        return super.onCreateOptionsMenu(menu)

    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        //Log.i("www", "onQueryTextChange: "+query.toString())
        if (query != null) {
            mainViewModel.listRepositories(query)
        }

        binding.root.hideSoftKeyboard()
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        Log.i("www", "onQueryTextChange: "+newText.toString())
        return true
    }


}