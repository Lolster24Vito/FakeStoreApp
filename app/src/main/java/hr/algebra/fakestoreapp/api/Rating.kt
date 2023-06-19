package hr.algebra.fakestoreapp.api

import com.google.gson.annotations.SerializedName

data class Rating(
    @SerializedName("rate") val rate : Double,
    @SerializedName("count") val count : Int
)
