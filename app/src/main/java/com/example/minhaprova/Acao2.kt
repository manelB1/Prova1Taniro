package com.example.minhaprova

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.minhaprova.BancoDeDados.LivroDBOpener
import com.example.minhaprova.databinding.ActivityAcao2Binding

class Acao2 : AppCompatActivity() {
    lateinit var binding:ActivityAcao2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this,R.layout.activity_acao2)

        binding.apply {
            buttonConfirm.setOnClickListener{
                var nome = binding.editTextNome.text
                var autor = binding.editTextAutor.text
                var ano = binding.editTextAno.text
                var nota = binding.ratingBar.rating
                var livro = Livro(0, nome.toString(), autor.toString(), ano.toString().toInt(), nota)
                var database = LivroDBOpener(this@Acao2)
                database.insertLivro(livro)

                setResult(Activity.RESULT_OK, Intent().putExtra("result","Cadastrado"))
                finish()

            }

            buttonCancelar.setOnClickListener {
                setResult(Activity.RESULT_CANCELED)
                finish()
            }


        }


    }
}
