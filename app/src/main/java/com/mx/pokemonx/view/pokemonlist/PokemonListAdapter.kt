package com.mx.pokemonx.view.pokemonlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mx.pokemonx.R
import com.mx.pokemonx.model.Pokemon
import com.mx.pokemonx.model.PokemonResult
import com.mx.pokemonx.service.PokemonApiService
import com.mx.pokemonx.utils.Utilidades
import kotlinx.android.synthetic.main.activity_pokemon_data.*
import kotlinx.android.synthetic.main.card_pokemon_search.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PokemonListAdapter(val pokemonClick: (Int) -> Unit): RecyclerView.Adapter<PokemonListAdapter.SearchViewHolder>() {
    var pokemonList: List<PokemonResult> = emptyList<PokemonResult>()

    fun setData(list: List<PokemonResult>){
        pokemonList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {

            return SearchViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.card_pokemon_search, parent, false)
            )

    }

    override fun getItemCount(): Int {
        return pokemonList.size
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        val pokemon = pokemonList[position]
        Glide.with(holder.itemView).load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/shiny/${position + 1}.png").into(holder.itemView.pokemonImageView)
        holder.itemView.pokemonText.text = "#${position + 1} - ${pokemon.name}"
        if(Utilidades.visualizacion == Utilidades.LIST) {
            holder.itemView.pokemonText.textSize = 18f
        }else{
            holder.itemView.pokemonText.textSize = 12f
        }
        holder.itemView.setOnClickListener { pokemonClick(position + 1) }

    }

    class SearchViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
}