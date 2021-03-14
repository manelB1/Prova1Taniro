package com.example.minhaprova

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.minhaprova.*
import com.example.minhaprova.ViewModel.VModel


import com.example.minhaprova.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar


class MainActivity : AppCompatActivity() {

    lateinit var binding:ActivityMainBinding
    lateinit var viewModel: VModel

    var bem_vindo =0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        viewModel = ViewModelProvider(this).get(VModel::class.java)

        binding.texto1.text = viewModel.texto1
        binding.texto2.text = viewModel.texto2


        binding.apply {

            buttonAcao1.setOnClickListener{
               val intent = Intent(this@MainActivity, Acao1::class.java)
                startActivityForResult(intent, 1)
            }

            buttonAcao2.setOnClickListener{
               var dialog = DialogCoffe()
               dialog.show(supportFragmentManager,"Dialog")
            }

            buttonAcao3.setOnClickListener {
              val intent = Intent(this@MainActivity, Acao2::class.java)
                startActivityForResult(intent, 3)
            }

            buttonAcao4.setOnClickListener {
                val intent = Intent(this@MainActivity, Acao3::class.java)
                startActivity(intent)
            }

        }
        
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(requestCode){
            1->{
                when(resultCode){
                        Activity.RESULT_OK->{
                          binding.apply {
                              val result = data?.extras?.getString("texto")
                              texto1.text = data?.getStringExtra("resposta").toString()
                              viewModel.texto1 = data?.getStringExtra("resposta").toString()
                              binding.texto1.text = result
                              invalidateAll()
                          }
                        }
                    Activity.RESULT_CANCELED->{
                        Snackbar.make(binding.buttonAcao1,"Atualização Cancelada",Snackbar.LENGTH_SHORT)
                            .setAction("Cancelar"){
                                Toast.makeText(this,"Cancelado", Toast.LENGTH_LONG).show()
                            }
                            .show()

                    }
                }
            }
            2->{
                when(resultCode){
                    Activity.RESULT_OK->{
                        binding.apply {
                            texto2.text = data?.getStringExtra("resposta2").toString()
                            viewModel!!.texto2 = data?.getStringExtra("resposta").toString()
                            invalidateAll()
                        }
                    }
                    Activity.RESULT_CANCELED->{
                        Toast.makeText(this,"Cancelado",Toast.LENGTH_SHORT).show()
                    }
                }

            }
        }

    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)
        outState.putInt("bem_vindo", bem_vindo)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        bem_vindo = savedInstanceState.getInt("Bem Vindo")

    }

    override fun onResume() {
        super.onResume()

        if(bem_vindo==0){
            Toast.makeText(this, "Bem Vindo",Toast.LENGTH_LONG).show()
            bem_vindo = 1
        }
    }
    }


