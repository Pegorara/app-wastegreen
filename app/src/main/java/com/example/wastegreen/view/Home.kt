package com.example.wastegreen.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import com.example.wastegreen.R
import com.example.wastegreen.adapter.PlanosAdapter
import com.example.wastegreen.databinding.ActivityHomeBinding
import com.example.wastegreen.model.Planos

class Home : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var planosAdapter: PlanosAdapter
    private val listaPlanos: MutableList<Planos> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()
        val nome = intent.extras?.getString("nome")

        binding.txtNomeUsuario.text = "Bem Vindo,$nome"
        val recyclerViewPlanos = binding.recyclerViewPlanos
        recyclerViewPlanos.layoutManager = GridLayoutManager(this,2)
        planosAdapter = PlanosAdapter(this,listaPlanos)
        recyclerViewPlanos.setHasFixedSize(true)
        recyclerViewPlanos.adapter = planosAdapter
        getPlanos()

        binding.btAgendar.setOnClickListener {
            val intent = Intent(this,Agendamento::class.java)
            intent.putExtra("nome",nome)
            startActivity(intent)
        }

    }

    private fun getPlanos(){

        val plano1 = Planos(R.drawable.img1,"Entrega no Pátio")
        listaPlanos.add(plano1)

        val plano2 = Planos(R.drawable.img1,"Coleta Mensal")
        listaPlanos.add(plano2)

        val plano3 = Planos(R.drawable.img2,"Coleta Mensal Bambona")
        listaPlanos.add(plano3)
    }
}