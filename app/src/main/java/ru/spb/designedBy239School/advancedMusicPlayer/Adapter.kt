package ru.spb.designedBy239School.advancedMusicPlayer

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import androidx.recyclerview.widget.RecyclerView

class Adapter(private val values: List<String>):RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder{
        val itemView = LayoutInflater.from(parent?.context).inflate(R.layout.list_item_view, parent, false)
        return View(itemView!)
    }

    override fun getItemCount(): Int {
        return values.size
    }



}