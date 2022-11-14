package com.dev_david.lojakloset.activities.FormLogin

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.dev_david.lojakloset.DialogCarregando
import com.dev_david.lojakloset.R
import com.dev_david.lojakloset.activities.FormCadastro.FormCadastro
import com.dev_david.lojakloset.activities.TelaProdutos.TelaProdutos
import com.dev_david.lojakloset.databinding.ActivityFormLoginBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth

class FormLogin : AppCompatActivity() {

    lateinit var binding: ActivityFormLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFormLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar!!.hide()

        val dialogCarregando = DialogCarregando(this)

        binding.btEntrar.setOnClickListener { view->
            val email = binding.editEmail.text.toString()
            val senha = binding.editSenha.text.toString()

            if (email.isEmpty() || senha.isEmpty()){
                val snackbar = Snackbar.make(view,"Preencha todos os campos",Snackbar.LENGTH_SHORT)
                snackbar.setBackgroundTint(Color.RED)
                snackbar.setTextColor(Color.WHITE)
                snackbar.show()
            } else{
                FirebaseAuth.getInstance().signInWithEmailAndPassword(email,senha).addOnCompleteListener { tarefa->
                    if (tarefa.isSuccessful){

                        Snackbar.make(view,"Login realizado com sucesso",Snackbar.LENGTH_SHORT)
                        dialogCarregando.iniciarCarregamentoAlertDialog()
                        Handler(Looper.getMainLooper()).postDelayed({
                            irParaTelaProduto()
                            dialogCarregando.liberarAlertDialog()

                        }, 3000)

                    }

                }.addOnFailureListener {
                    val snackbar = Snackbar.make(view, "Erro login", Snackbar.LENGTH_SHORT)
                    snackbar.setBackgroundTint(Color.RED)
                    snackbar.setTextColor(Color.WHITE)
                    snackbar.show()
                }
            }
        }

        binding.txtTelaCadastro.setOnClickListener {

            val intent = Intent(this,FormCadastro::class.java)
            startActivity(intent)
        }
    }

    private fun irParaTelaProduto(){

        val intent = Intent(this,TelaProdutos::class.java)
        startActivity(intent)
        finish()

    }

    override fun onStart() {
        super.onStart()

        val usuarioAtual = FirebaseAuth.getInstance().currentUser

        if (usuarioAtual != null){
            irParaTelaProduto()

        }
    }
}