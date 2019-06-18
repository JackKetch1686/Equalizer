package ru.spb.designedBy239School.advancedMusicPlayer.adapter

import android.content.Context
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.spb.designedBy239School.advancedMusicPlayer.R

class MyRecyclerViewAdapter(var listItems: List<RecyclerItem>, var mContext: Context, val onSongListner: OnSongListner) :
    RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var v: View = LayoutInflater.from(parent.context).inflate(R.layout.recycler_item, parent, false)
        return ViewHolder(v, onSongListner)
    }

    override fun getItemCount(): Int {
        return listItems.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val itemList: RecyclerItem = listItems[position]
        holder.song_name.text= itemList.SongName
        holder.artist_name.text = itemList.SongAuthorName
        holder.album_name.text = itemList.Album
        if (itemList.byteArray!=null) {
            holder.picture.setImageBitmap(BitmapFactory.decodeByteArray(itemList.byteArray, 0, itemList.byteArray.size))
        } else{
            holder.picture.setImageResource(R.drawable.music_notes)
        }
    }

    class ViewHolder( var view: View, var onSongListner: OnSongListner) : RecyclerView.ViewHolder(view), View.OnClickListener{
        override fun onClick(p0: View?) {
            onSongListner.onNoteCLick(adapterPosition)
        }

        var song_name: TextView = view.findViewById(R.id.name_of_song)
        var artist_name: TextView = view.findViewById(R.id.name_of_artist)
        var album_name : TextView = view.findViewById(R.id.album)
        var picture: ImageView =   view.findViewById(R.id.image)
        init {
            view.setOnClickListener(this)
        }

    }
    interface OnSongListner{
        fun onNoteCLick(position: Int)
    }
}