package com.dev_david.lojakloset.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dev_david.lojakloset.DetalhesProduto.DetalhesProduto
import com.dev_david.lojakloset.databinding.ProdutoItemBinding
import com.dev_david.lojakloset.model.Produto

class AdapterProduto (val context: Context, val lista_produtos: MutableList<Produto>):RecyclerView.Adapter<AdapterProduto.ProdutoViewHolder>()
{

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProdutoViewHolder {
        val item_lista = ProdutoItemBinding.inflate(LayoutInflater.from(context),parent,false)
        return ProdutoViewHolder(item_lista)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ProdutoViewHolder, position: Int) {
        Glide.with(context).load(lista_produtos.get(position).foto).into(holder.fotoProduto)
        holder.nomeProduto.text = lista_produtos.get(position).nome
        holder.precoProduto.text = "R$ ${lista_produtos.get(position).preco}"

        holder.itemView.setOnClickListener {
            val intent = Intent(context,DetalhesProduto::class.java)
            intent.putExtra("foto",lista_produtos.get(position).foto)
            intent.putExtra("nome",lista_produtos.get(position).nome)
            intent.putExtra("preco",lista_produtos.get(position).preco)
            context.startActivity(intent)
        }
    }

    override fun getItemCount() = lista_produtos.size

    inner class ProdutoViewHolder(binding: ProdutoItemBinding) : RecyclerView.ViewHolder(binding.root) {

        val fotoProduto = binding.fotoProduto
        val nomeProduto = binding.nomeProduto
        val precoProduto = binding.precoProduto

    }
}