package com.mx.pokemonx.view.splash

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mx.pokemonx.view.pokemonlist.PokemonListActivity


class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val intent = Intent(this, PokemonListActivity::class.java)
        startActivity(intent)
        finish()
    }
}