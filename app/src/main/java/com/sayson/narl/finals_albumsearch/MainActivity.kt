package com.sayson.narl.finals_albumsearch

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle

import android.support.v7.widget.LinearLayoutManager

import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Response
import java.io.IOException


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        search.setOnQueryTextListener(object : android.widget.SearchView.OnQueryTextListener {

            override fun onQueryTextChange(newText: String): Boolean {

                return false
            }

            override fun onQueryTextSubmit(query: String): Boolean {
                filter(query)
                return false
            }

        })


    }
    private fun filter(text: String) {
        val albumList = ArrayList<AlbumDetails>()
        var albumName:String = text
        var url = "http://ws.audioscrobbler.com/2.0/?method=album.search&album=$albumName&api_key=3be9215e1cb86871cb8cc8a5b5955835&format=json"
        var request = okhttp3.Request.Builder().url(url).build()
        var client = OkHttpClient()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call?, e: IOException?) {

            }
            override fun onResponse(call: Call?, response: Response?) {
                val json = response?.body()?.string()
                val gson = GsonBuilder().create()
                val album = gson.fromJson(json, Album::class.java)
                runOnUiThread{

                    for(album in album.results.albummatches.album){
                        albumList.add(album)
                    }
                    println(album.results.albummatches.album)

                    val adapterStat = AlbumAdapter(this@MainActivity, albumList)
                    val layoutManagerStat = LinearLayoutManager(this@MainActivity)

                    recyclerView.adapter = adapterStat
                    recyclerView.layoutManager = layoutManagerStat
                }
            }

        })



    }

}
