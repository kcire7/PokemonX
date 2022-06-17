package com.mx.pokemonx.view.pokemondata

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.mx.pokemonx.R
import kotlinx.android.synthetic.main.activity_pokemon_data.*

class PokemonDataActivity : AppCompatActivity() {
    lateinit var viewModel: PokemonDataViewModel
    private lateinit var toolbar: Toolbar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokemon_data)
        viewModel = ViewModelProvider(this).get(PokemonDataViewModel::class.java)
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        initInterface()

    }
    private fun initInterface(){
        val id = intent.extras?.get("id") as Int

        viewModel.getPokemonInfo(id)

        viewModel.pokemonInfo.observe(this, Observer { pokemon ->
            var habilidades = ""
            var tipos = ""
            pokemon.types.forEach{
                tipos = tipos + "\n - " + it.type.name
            }
            pokemon.abilities.forEach{
                habilidades =  habilidades+ "\n * " + it.ability.name
            }
            nameTextView.text = pokemon.name
            orderText.text = "# ${pokemon.order}"
            typeText.text = "Tipo: ${tipos}"
            abilitiesText.text = "Habilidades: ${habilidades}"

            Glide.with(this).load(pokemon.sprites.frontDefault).into(imageView)
            //println(pokemon.abilities[0].ability.name)
        })
    }
}