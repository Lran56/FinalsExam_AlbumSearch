package com.sayson.narl.finals_albumsearch
import com.google.gson.annotations.SerializedName
/**
 * Created by Lran on 3/21/2018.
 */

data class Album(
        val results: Results
)
data class Results(
        val albummatches: AlbumMatches
)
data class AlbumMatches(
        val album: ArrayList<AlbumDetails>
)
data class AlbumDetails(
        val name: String,
        val artist: String,
        val image: ArrayList<AlbumImg>
)
data class AlbumImg(
        @SerializedName("#text") val text: String,
        val size: String
)