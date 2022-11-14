package com.dev_david.lojakloset.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dev_david.lojakloset.activities.pedidos.Pedidos
import com.dev_david.lojakloset.databinding.PedidoItemBinding
import com.dev_david.lojakloset.model.Pedido

class AdapterPedido (val context: Context, val lista_pedidos: MutableList<Pedido>):
    RecyclerView.Adapter<AdapterPedido.PedidoViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PedidoViewHolder {
        val item_lista = PedidoItemBinding.inflate(LayoutInflater.from(context),parent,false)
        return PedidoViewHolder(item_lista)
    }

    override fun onBindViewHolder(holder: PedidoViewHolder, position: Int) {
        holder.txtEndereco.text = lista_pedidos.get(position).endereco
        holder.txtCelular.text = lista_pedidos.get(position).celular
        holder.txtNome.text = lista_pedidos.get(position).produto
        holder.txtPreco.text = lista_pedidos.get(position).preco
        holder.txtTamanhoCalcado.text = lista_pedidos.get(position).tamanho_calcado
        holder.txtStatusPagamento.text = lista_pedidos.get(position).status_pagamento
        holder.txtStatusEntrega.text = lista_pedidos.get(position).status_entrega


    }

    override fun getItemCount() = lista_pedidos.size

    inner class PedidoViewHolder(binding: PedidoItemBinding ) : RecyclerView.ViewHolder(binding.root) {

        val txtEndereco = binding.txtEndereco
        val txtCelular = binding.txtCelular
        val txtNome = binding.txtnomeProduto
        val txtPreco = binding.txtprecoProduto
        val txtTamanhoCalcado = binding.txttamanhoCalcado
        val txtStatusPagamento = binding.txtstatusPagamento
        val txtStatusEntrega = binding.txtstatusEntrega

    }


}