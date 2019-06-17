package ru.spb.designedBy239School.advancedMusicPlayer

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.appcompat.widget.PopupMenu

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
        holder.song_name.text = itemList.song
        holder.artist_name.text = itemList.artist
        holder.image = itemList.picture
//        holder.text_optin.setOnClickListener {
//            var popupMenu: PopupMenu = PopupMenu( mContext, holder.text_optin)
//            popupMenu.inflate(R.menu.option_menu)
//            popupMenu.setOnMenuItemClickListener{
//
//            }
//
//        }
    }

    class ViewHolder(var view: View) : RecyclerView.ViewHolder(view) {
        var image: ImageView = view.findViewById(R.id.image)
        var song_name: TextView = view.findViewById(R.id.song_name)
        var artist_name: TextView = view.findViewById(R.id.artist_name)
        var text_optin: TextView = view.findViewById(R.id.textOption)
    }

}

