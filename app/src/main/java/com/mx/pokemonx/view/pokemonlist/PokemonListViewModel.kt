package com.mx.pokemonx.view.pokemonlist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mx.pokemonx.model.Pokemon
import com.mx.pokemonx.model.PokemonApiResp
import com.mx.pokemonx.model.PokemonResult
import com.mx.pokemonx.service.PokemonApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PokemonListViewModel: ViewModel() {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://pokeapi.co/api/v2/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val service: PokemonApiService = retrofit.create(PokemonApiService::class.java)

    val pokemonList = MutableLiveData<List<PokemonResult>>()
    val pokemonInfo = MutableLiveData<Pokemon>()

    fun getPokemonList(){
        val call = service.getPokemonList(100,0)

        call.enqueue(object : Callback<PokemonApiResp> {
            override fun onResponse(call: Call<PokemonApiResp>, response: Response<PokemonApiResp>) {
                response.body()?.results?.let { list ->
                    pokemonList.postValue(list)
                }

            }

            override fun onFailure(call: Call<PokemonApiResp>, t: Throwable) {
                call.cancel()
            }

        })

    }
}