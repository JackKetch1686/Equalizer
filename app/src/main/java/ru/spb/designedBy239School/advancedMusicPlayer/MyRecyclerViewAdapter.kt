package ru.spb.designedBy239School.advancedMusicPlayer

import android.content.Context
import android.graphics.drawable.Drawable
import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyRecyclerViewAdapter(var listItems: List<RecyclerItem>, var mContext: Context) :
    RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var v: View = LayoutInflater.from(parent.context).inflate(R.layout.recycler_item, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return listItems.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val itemList: RecyclerItem = listItems[position]
        holder.song_name.text= itemList.SongName
        holder.artist_name.text = itemList.SongAuthorName
        holder.album_name.text = itemList.Album
        holder.picture.setImageResource(itemList.photoId)
    }

    class ViewHolder( var view: View) : RecyclerView.ViewHolder(view) {
        var song_name: TextView = view.findViewById(R.id.name_of_song)
        var artist_name: TextView = view.findViewById(R.id.name_of_artist)
        var album_name : TextView = view.findViewById(R.id.album)
        var picture: ImageView =   view.findViewById<ImageView>(R.id.image_of_song)


    }
}