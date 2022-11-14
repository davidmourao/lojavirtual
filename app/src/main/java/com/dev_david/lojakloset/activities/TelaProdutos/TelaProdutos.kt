package com.dev_david.lojakloset.activities.TelaProdutos

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.GridLayoutManager
import com.dev_david.lojakloset.DialogPerfilUsuario
import com.dev_david.lojakloset.R
import com.dev_david.lojakloset.activities.FormLogin.FormLogin
import com.dev_david.lojakloset.activities.pedidos.Pedidos
import com.dev_david.lojakloset.adapter.AdapterProduto
import com.dev_david.lojakloset.databinding.ActivityTelaProdutosBinding
import com.dev_david.lojakloset.model.DB
import com.dev_david.lojakloset.model.Produto
import com.google.firebase.auth.FirebaseAuth

class TelaProdutos : AppCompatActivity() {

    lateinit var binding: ActivityTelaProdutosBinding
    lateinit var adapterProduto:AdapterProduto
    var listaProdutos: MutableList<Produto> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTelaProdutosBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val recycler_produtos = binding.recyclerProdutos
        recycler_produtos.layoutManager = GridLayoutManager(this,2)
        recycler_produtos.setHasFixedSize(true)
        adapterProduto = AdapterProduto(this,listaProdutos)
        recycler_produtos.adapter = adapterProduto

        val db = DB()
        db.obterListaProdutos(listaProdutos,adapterProduto)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_principal,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {


        when(item.itemId){
            R.id.perfil -> iniciarDialogPerfilUsuario()
            R.id.pedidos -> iniciarTelaPedidos()
            R.id.deslogar -> deslogarUsuario()

        }
        return super.onOptionsItemSelected(item)
    }

    private fun iniciarDialogPerfilUsuario(){
        val dialogPerfilUsuario = DialogPerfilUsuario(this)
        dialogPerfilUsuario.iniciarPerfilUsuario()
        dialogPerfilUsuario.recuperarDadosUsuarioBanco()
    }

    private fun iniciarTelaPedidos(){
        val intent = Intent(this,Pedidos::class.java)
        startActivity(intent)
    }

    private fun deslogarUsuario(){
        FirebaseAuth.getInstance().signOut()
        val intent = Intent(this,FormLogin::class.java)
        startActivity(intent)
        finish()
    }
}