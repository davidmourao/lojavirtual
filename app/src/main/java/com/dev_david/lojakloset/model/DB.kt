package com.dev_david.lojakloset.model

import android.util.Log
import android.widget.TextView
import com.dev_david.lojakloset.DialogPerfilUsuario
import com.dev_david.lojakloset.adapter.AdapterPedido
import com.dev_david.lojakloset.adapter.AdapterProduto
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
import java.util.*

class DB {
    fun salvarDadosUsuario(nome: String){
        val usuarioID = FirebaseAuth.getInstance().currentUser!!.uid
        val db = FirebaseFirestore.getInstance()

        val usuarios = hashMapOf(
            "nome" to nome
        )

        val documentReference: DocumentReference = db.collection("Usuarios").document(usuarioID)
        documentReference.set(usuarios).addOnSuccessListener {
            Log.d("DB","Sucesso")
        }.addOnFailureListener { erro->
            Log.d("DB_USUARIOS", "Erro ao salvar dados ${erro.printStackTrace()}" )

        }

    }

    fun recuperarDadosUsuarioPerfil(nomeUsuario: TextView, emailUsuario: TextView){

        val usuarioID = FirebaseAuth.getInstance().currentUser!!.uid
        val email = FirebaseAuth.getInstance().currentUser!!.email
        val db = FirebaseFirestore.getInstance()

        val documentReference: DocumentReference = db.collection("Usuarios").document(usuarioID)
        documentReference.addSnapshotListener { documento, error ->
            if (documento != null){
                nomeUsuario.text = documento.getString("nome")
                emailUsuario.text = email
            }
        }

    }

    fun obterListaProdutos(lista_produtos: MutableList<Produto>, adapter_produto: AdapterProduto){

        val db = FirebaseFirestore.getInstance()

        db.collection("Produtos").get()
            .addOnCompleteListener { tarefa ->
                if (tarefa.isSuccessful){
                    for (documento in tarefa.result!!){
                        val produto = documento.toObject(Produto::class.java)
                        lista_produtos.add(produto)
                        adapter_produto.notifyDataSetChanged()

                    }
                }
            }
    }

    fun salvarDadosPedidoUsuario(
        endereco: String,
        celular: String,
        produto: String,
        preco: String,
        tamanho_calcado: String,
        status_pagamento: String,
        status_entrega: String,
    ){

        var db = FirebaseFirestore.getInstance()
        var usuarioId = FirebaseAuth.getInstance().currentUser!!.uid
        var pedidoID = UUID.randomUUID().toString()

        val pedidos = hashMapOf(
            "endereco" to endereco,
            "celular" to celular,
            "produto" to produto,
            "preco" to preco,
            "tamanho_calcado" to tamanho_calcado,
            "status_pagamento" to status_pagamento,
            "status_entrega" to status_entrega

        )

        val documentoReference = db.collection("Usuario_Pedidos").document(usuarioId)
            .collection("Pedidos").document(pedidoID)
        documentoReference.set(pedidos).addOnSuccessListener {
            Log.d("db_pedidos", "Sucesso ao salvar os pedidos")
        }.addOnFailureListener {
            Log.d("error_pedido","erro ao salvar pedido ${it}")
        }

    }

    fun obterListaPedidos(lista_pedidos: MutableList<Pedido>, adapter_pedidos: AdapterPedido){

        var db = FirebaseFirestore.getInstance()
        var usuarioID = FirebaseAuth.getInstance().currentUser!!.uid

        db.collection("Usuario_Pedidos").document(usuarioID).collection("Pedidos")
            .get().addOnCompleteListener { tarefa->
                if (tarefa.isSuccessful){
                    for (documento in tarefa.result!!){
                        val pedidos = documento.toObject(Pedido::class.java)
                        lista_pedidos.add(pedidos)
                        adapter_pedidos.notifyDataSetChanged()

                    }
                }

            }

    }

}