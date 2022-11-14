package com.dev_david.lojakloset.activities.pedidos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.dev_david.lojakloset.R
import com.dev_david.lojakloset.adapter.AdapterPedido
import com.dev_david.lojakloset.databinding.ActivityPedidosBinding
import com.dev_david.lojakloset.model.DB
import com.dev_david.lojakloset.model.Pedido

class Pedidos : AppCompatActivity() {

    lateinit var binding: ActivityPedidosBinding
    lateinit var adapterPedidos: AdapterPedido
    var lista_pedidos:MutableList<Pedido> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPedidosBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val recyclerpedidos = binding.recyclerPedidos
        recyclerpedidos.layoutManager = LinearLayoutManager(this)
        recyclerpedidos.setHasFixedSize(true)
        adapterPedidos = AdapterPedido(this, lista_pedidos )
        recyclerpedidos.adapter = adapterPedidos

        val db = DB()
        db.obterListaPedidos(lista_pedidos,adapterPedidos)
    }
}