package com.dev_david.lojakloset.DetalhesProduto

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.dev_david.lojakloset.activities.Pagamento.Pagamento
import com.dev_david.lojakloset.databinding.ActivityDetalhesProdutoBinding
import com.google.android.material.snackbar.Snackbar


class DetalhesProduto : AppCompatActivity() {


    lateinit var binding: ActivityDetalhesProdutoBinding
    var tamanho_calcado = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityDetalhesProdutoBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val foto = intent.extras?.getString("foto")
        val nome = intent.extras?.getString("nome")
        val preco = intent.extras?.getString("preco")

        Glide.with(this).load(foto).into(binding.dtFotoProduto)
        binding.dtNomeProduto.text = nome
        binding.dtPrecoProduto.text = "R$ ${preco}"

        binding.btFinalizarPedido.setOnClickListener {
            val tamanhoCalcado38 = binding.tamanho38
            val tamanhoCalcado39 = binding.tamanho39
            val tamanhoCalcado40 = binding.tamanho40
            val tamanhoCalcado41 = binding.tamanho41
            val tamanhoCalcado42 = binding.tamanho42

            when(true){
                tamanhoCalcado38.isChecked -> tamanho_calcado = "38"
                tamanhoCalcado39.isChecked -> tamanho_calcado = "39"
                tamanhoCalcado40.isChecked -> tamanho_calcado = "40"
                tamanhoCalcado41.isChecked -> tamanho_calcado = "41"
                tamanhoCalcado42.isChecked -> tamanho_calcado = "42"
                else -> {}
            }

            if (!tamanhoCalcado38.isChecked && !tamanhoCalcado39.isChecked && !tamanhoCalcado40.isChecked
                && !tamanhoCalcado41.isChecked && !tamanhoCalcado42.isChecked){
                val snackbar = Snackbar.make(it,"Escolha o tamanho do calçado",Snackbar.LENGTH_SHORT)
                snackbar.setBackgroundTint(Color.RED)
                snackbar.setTextColor(Color.WHITE)
                snackbar.show()

            }else {
                val intent = Intent(this,Pagamento::class.java)
                intent.putExtra("tamanho_calcado",tamanho_calcado)
                intent.putExtra("nome",nome)
                intent.putExtra("preco",preco)
                startActivity(intent)


            }

        }
    }
}