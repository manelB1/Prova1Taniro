package com.example.minhaprova

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.minhaprova.databinding.ActivityAcao1Binding
import com.google.android.material.snackbar.Snackbar

class Acao1 : AppCompatActivity() {
    lateinit var binding:ActivityAcao1Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this,R.layout.activity_acao1)

        binding.apply {

            buttonOK.setOnClickListener{
                var intent = Intent()
                intent.putExtra("texto",editTextTextPersonName.text.toString())
                setResult(Activity.RESULT_OK,intent)
                finish()
            }


            buttonCancelar.setOnClickListener{
                setResult(Activity.RESULT_CANCELED)
                finish()
            }


        }


        }
    }


