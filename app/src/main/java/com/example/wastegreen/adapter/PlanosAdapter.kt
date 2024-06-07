package com.example.wastegreen.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.wastegreen.databinding.PlanosItemBinding
import com.example.wastegreen.model.Planos

class PlanosAdapter(private val context: Context, private val listaPlanos: MutableList<Planos>):
    RecyclerView.Adapter<PlanosAdapter.PlanosViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlanosViewHolder {
        val itemLista = PlanosItemBinding.inflate(LayoutInflater.from(context),parent,false)
        return PlanosViewHolder(itemLista)
    }

    override fun getItemCount() = listaPlanos.size

    override fun onBindViewHolder(holder: PlanosViewHolder, position: Int) {
        holder.imgPlanos.setImageResource(listaPlanos[position].img!!)
        holder.txtPlanos.text = listaPlanos[position].nome
    }

    inner class PlanosViewHolder(binding: PlanosItemBinding): RecyclerView.ViewHolder(binding.root){
        val imgPlanos = binding.imgPlanos
        val txtPlanos= binding.txtPlanos
    }
}