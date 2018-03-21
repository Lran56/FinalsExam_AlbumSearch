package com.sayson.narl.finals_albumsearch

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.content.Context
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import com.sayson.narl.finals_albumsearch.AlbumAdapter
import com.sayson.narl.finals_albumsearch.Album
import com.sayson.narl.finals_albumsearch.AlbumDetails
import com.sayson.narl.finals_albumsearch.AlbumMatches

import kotlinx.android.synthetic.main.al.view.*

/**
 * Created by Lran on 3/21/2018.
 */
class AlbumAdapter(val mContext: Context, val albumList: ArrayList<AlbumDetails>): RecyclerView.Adapter<AlbumViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {
        val layoutInflater = LayoutInflater.from(parent?.context)
        val cellForRow = layoutInflater.inflate(R.layout.al, parent, false)
        return AlbumViewHolder(cellForRow)
    }

    override fun getItemCount(): Int {
        return albumList.size
    }

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        val album: AlbumDetails = albumList[position]
        holder?.view?.txtAlbumName?.text = album.name
        holder?.view?.txtAlbumArtist?.text = album.artist
        val albumImage = holder?.view?.imgAlbum
        if(album.image.get(0).text != null)
        {

            Picasso.with(holder?.view?.context).load(album.image.get(0).text).into(albumImage)
        }
        else
        {
            Picasso.with(holder?.view?.context).load(R.drawable.ic_launcher_background).into(albumImage)
        }
    }
}
class AlbumViewHolder (val view: View): RecyclerView.ViewHolder(view){

}