package com.mx.pokemonx.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Pokemon (
    @Expose @SerializedName("id") val id: Int,
    @Expose @SerializedName("name") val name: String,
    @Expose @SerializedName("order") val order: Int,
    @Expose @SerializedName("types") val types: List<TypeElement>,
    @Expose @SerializedName("abilities") val abilities: List<AbilityElement>,
    @Expose @SerializedName("sprites") val sprites: Sprites
)
data class TypeElement (
    @Expose @SerializedName("type") val type: TypeType
)
data class TypeType (
    @Expose @SerializedName("name") val name: String?
)
data class AbilityElement (
    @Expose @SerializedName("ability") val ability: AbilityAbility
)
data class AbilityAbility (
    @Expose @SerializedName("name") val name: String?
)
data class Sprites (
    @Expose @SerializedName("front_default") val frontDefault: String?,
    @Expose @SerializedName("front_shiny") val frontShiny: String?
)
