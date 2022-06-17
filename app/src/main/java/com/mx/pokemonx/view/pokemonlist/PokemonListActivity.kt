package com.mx.pokemonx.view.pokemonlist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.mx.pokemonx.R
import com.mx.pokemonx.utils.Utilidades
import com.mx.pokemonx.view.pokemondata.PokemonDataActivity
import kotlinx.android.synthetic.main.activity_pokemon_list.*

class PokemonListActivity : AppCompatActivity() {
    private lateinit var viewModel: PokemonListViewModel
    private lateinit var toolbar: Toolbar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokemon_list)
        viewModel = ViewModelProvider(this).get(PokemonListViewModel::class.java)
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        initInterface()
    }
    private fun initInterface(){
        if(Utilidades.visualizacion == Utilidades.LIST) {
            pokemonlistRecyclerView.layoutManager = LinearLayoutManager(this)
        }else{
            pokemonlistRecyclerView.layoutManager = GridLayoutManager(this,2)
        }
        pokemonlistRecyclerView.adapter = PokemonListAdapter{
            val intent = Intent(this, PokemonDataActivity::class.java)
            intent.putExtra("id", it)
            startActivity(intent)
        }

        viewModel.getPokemonList()

        viewModel.pokemonList.observe(this, Observer { list ->
            (pokemonlistRecyclerView.adapter as PokemonListAdapter).setData(list)
        })
    }

    fun onClick(view: View) {
        when(view.getId()){
            R.id.btnList-> Utilidades.visualizacion=Utilidades.LIST
            R.id.btnGrid-> Utilidades.visualizacion=Utilidades.GRID
        }
        initInterface()
    }
}