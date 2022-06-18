package br.com.antoniojose.app.repositories.ui

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import br.com.antoniojose.app.repositories.data.model.Repo
import br.com.antoniojose.app.repositories.databinding.ItemRepoBinding
import com.bumptech.glide.Glide

class AdapterRepo: ListAdapter<Repo, AdapterRepo.ViewHolder>(DiffiCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
           val layoutInflater = LayoutInflater.from( parent.context )
           val binding = ItemRepoBinding.inflate( layoutInflater, parent, false)
           return ViewHolder( binding )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.bind( getItem(position) )
    }

    class ViewHolder(private val binding: ItemRepoBinding): RecyclerView.ViewHolder(binding.root){
          fun bind(item: Repo){
               binding.tvTituloRepo.text =  item.name
               binding.tvDescricao.text = item.description
               binding.chipStar.text = item.stargazersCount.toString()
               binding.tvLinguagem.text = item.language

               Glide.with(binding.root.context).load( item.owner.avatarURL ).into(binding.ivAvatar)

               binding.root.setOnClickListener {
                   val intent = Intent(binding.root.context, DetailRepoActivity::class.java)
                   intent.putExtra("repo", item)
                   binding.root.context.startActivity( intent )
               }
          }
    }

    class DiffiCallback: DiffUtil.ItemCallback<Repo>(){
        override fun areItemsTheSame(oldItem: Repo, newItem: Repo) = oldItem == newItem
        override fun areContentsTheSame(oldItem: Repo, newItem: Repo) = oldItem.id == newItem.id

    }

}