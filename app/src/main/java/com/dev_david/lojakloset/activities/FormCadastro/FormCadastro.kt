package com.dev_david.lojakloset.activities.FormCadastro

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dev_david.lojakloset.R
import com.dev_david.lojakloset.databinding.ActivityFormCadastroBinding
import com.dev_david.lojakloset.model.DB
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException

class FormCadastro : AppCompatActivity() {

    lateinit var binding: ActivityFormCadastroBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFormCadastroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar!!.hide()


        val db = DB ()



        binding.btCadastrar.setOnClickListener {

            val nome = binding.editNome.text.toString()
            val email = binding.editEmail.text.toString()
            val senha = binding.editSenha.text.toString()

            if (nome.isEmpty() || email.isEmpty() || senha.isEmpty()){
                val snackbar = Snackbar.make(it, "Preencha todos os campos", Snackbar.LENGTH_SHORT)
                snackbar.setBackgroundTint(Color.RED)
                snackbar.setTextColor(Color.WHITE)
                snackbar.show()
            }else{
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(email,senha).addOnCompleteListener { tarefa->
                    if (tarefa.isSuccessful){
                        db.salvarDadosUsuario(nome)
                        val snackbar = Snackbar.make(it, "Usuario cadastrado com sucesso", Snackbar.LENGTH_SHORT)
                        snackbar.setBackgroundTint(Color.BLUE)
                        snackbar.setTextColor(Color.WHITE)
                        snackbar.show()

                    }

                }.addOnFailureListener { erroCadastro ->

                    val mensagemErro = when(erroCadastro){
                        is FirebaseAuthWeakPasswordException -> "Digite uma senha com no minimo 6 caracteres"
                        is FirebaseAuthUserCollisionException -> "Esta conta ja foi cadastrada"
                        is FirebaseNetworkException -> "Sem conexão"
                        else -> "Erro ao cadastrar usuário"

                    }
                    val snackbar = Snackbar.make(it, mensagemErro, Snackbar.LENGTH_SHORT)
                    snackbar.setBackgroundTint(Color.RED)
                    snackbar.setTextColor(Color.WHITE)
                    snackbar.show()

                }
            }
        }
    }
}