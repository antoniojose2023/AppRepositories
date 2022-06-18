package br.com.antoniojose.app.repositories.ui

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.antoniojose.app.repositories.R
import br.com.antoniojose.app.repositories.data.model.Repo
import br.com.antoniojose.app.repositories.databinding.ActivityDetailRepoBinding
import com.bumptech.glide.Glide

class DetailRepoActivity : AppCompatActivity() {

    private val binding by lazy {  ActivityDetailRepoBinding.inflate(layoutInflater)  }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

       val repo = intent.getSerializableExtra("repo") as Repo
       repo.let {
           binding.tvTitleRepoDetail.text = it.name
           binding.tvDescriptionRepoDetail.text = it.description
           binding.tvLanguageRepoDetail.text = it.language
           binding.tvUserRepoDetail.text = it.owner.login

           Glide.with(this).load(it.owner.avatarURL).into(binding.ivAvatarDetailRepo)

           val url = it.htmlURL
           binding.buttonNavegarRepositorioDetail.setOnClickListener {
                 val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                 startActivity(Intent.createChooser(intent, "Escolha seu navegador padrão."))
           }
       }
    }
}