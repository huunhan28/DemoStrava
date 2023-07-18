package com.example.demostrava.data.model.output

import com.google.gson.annotations.SerializedName

data class StreamModel(
    @SerializedName("latlng"   ) var latlng   : Latlng?   = Latlng(),
    @SerializedName("distance" ) var distance : Distance? = Distance()
)

data class Latlng (
    @SerializedName("data") var data : ArrayList<ArrayList<Double>> = arrayListOf(),
    @SerializedName("series_type") var seriesType : String? = null,
    @SerializedName("original_size") var originalSize : Int? = null,
    @SerializedName("resolution") var resolution : String? = null
)

data class Distance (
    @SerializedName("data") var data : ArrayList<Double> = arrayListOf(),
    @SerializedName("series_type") var seriesType : String? = null,
    @SerializedName("original_size") var originalSize : Int? = null,
    @SerializedName("resolution") var resolution : String? = null
)