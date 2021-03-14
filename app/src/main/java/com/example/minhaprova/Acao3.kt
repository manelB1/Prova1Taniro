package com.example.minhaprova

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.minhaprova.BancoDeDados.LivroDBOpener
import com.example.minhaprova.databinding.ActivityAcao3Binding

class Acao3 : AppCompatActivity() {
    var cont = 1
    lateinit var binding:ActivityAcao3Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this,R.layout.activity_acao3)

        var db = LivroDBOpener(this)
        var livro = db.findById(cont)
        var livros = db.findAll()

        binding.apply {
            textViewTitulo.text = "Titulo: ${livro.nome}"
            textViewAutor.text = "Autor: ${livro.autor}"
            textViewAno.text = "Ano: ${livro.ano}"
            textViewNota.text = "Nota: ${livro.nota}"

            buttonProximo.setOnClickListener{
                var livro = db.findById(++cont)
                textViewTitulo.text = "Titulo: ${livro.nome}"
                textViewAutor.text = "Autor: ${livro.autor}"
                textViewAno.text = "Ano: ${livro.ano}"
                textViewNota.text = "Nota: ${livro.nota}"
                if (cont > 1) binding.buttonAnterior.isEnabled = true
                if (cont == livros.size) binding.buttonProximo.isEnabled = false
            }

            buttonAnterior.setOnClickListener{
                if (cont > 1) {
                    buttonProximo.isEnabled = true
                }

                var livro = db.findById(--cont)
                textViewTitulo.text = "Titulo: ${livro.nome}"
                textViewAutor.text = "Autor: ${livro.autor}"
                textViewAno.text = "Ano: ${livro.ano}"
                textViewNota.text = "Nota: ${livro.nota}"

                if (cont == 1) binding.buttonAnterior.isEnabled = false
            }
        }
    }
}