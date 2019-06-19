package ru.spb.designedBy239School.advancedMusicPlayer

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.media.MediaMetadataRetriever
import android.os.Bundle
import android.os.Environment
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.spb.designedBy239School.advancedMusicPlayer.adapter.MyRecyclerViewAdapter
import ru.spb.designedBy239School.advancedMusicPlayer.adapter.RecyclerItem
import ru.spb.designedBy239School.advancedMusicPlayer.service.BackgroundAudioService
import java.io.File

class MainActivity : AppCompatActivity(),MyRecyclerViewAdapter.OnSongListner{

    val recyclerItems = returnRecyclerItems()
    override fun onNoteCLick(position: Int) {
        startService(Intent(this,BackgroundAudioService::class.java).putExtra("path", recyclerItems[position].fullName))

        Log.d("ONCLICKLISTNER","MainActivity: "+position.toString())

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), 0)
        }

        var recyclerView: RecyclerView = findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val adapterR = MyRecyclerViewAdapter(recyclerItems, this,this)
        recyclerView.adapter = adapterR
    }


     fun returnRecyclerItems(): ArrayList<RecyclerItem> {
        val recyclerItems: ArrayList<RecyclerItem> = ArrayList()
        var listMusic = getPlayListStrings(Environment.getExternalStorageDirectory())
        for (i in listMusic) {
            var m = MediaMetadataRetriever()
            m.setDataSource(i["path"])
            var image = m.getEmbeddedPicture()
            var artist = "artist: " + m.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ARTIST)
            var album = "" + m.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ALBUM)
            var song = m.extractMetadata(MediaMetadataRetriever.METADATA_KEY_TITLE)
            if (song == null) song = i["name"]
            recyclerItems.add(
                RecyclerItem(
                    song,
                    artist,
                    album,
                    image,
                    i["path"].toString()
                )
            )
        }
         return recyclerItems
    }

    private fun getPlayListStrings(inputFile : File) : ArrayList<HashMap<String,String>> {
        val filelist : ArrayList<HashMap<String,String>> = ArrayList()
        try {
            val files = inputFile.listFiles() //here you will get NPE if directory doesn't contains any file,handle it like this.
            for (file in files) {
                if (file.isDirectory) {
                    if (getPlayListStrings(file) != null) {
                        filelist.addAll(getPlayListStrings(file))
                    } else {

                        break
                    }
                } else if (file.getName().endsWith(".mp3")) {
                    val song = HashMap<String, String>()
                    song["name"] = file.name
                    song["path"] = file.absolutePath
                    filelist.add(song)
                }
            }
            return filelist
        } catch (e: Exception) {
            return ArrayList()
        }
    }

}



//        var m = MediaMetadataRetriever()
//        m.setDataSource(this, Uri.parse("android.resource://ru.spb.designedBy239School.advancedMusicPlayer/raw/heavy"))
//        var artist = "artist: "+ m.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ARTIST)
//        var album = ""+ m.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ALBUM)
//        var song = ""+m.extractMetadata(MediaMetadataRetriever.METADATA_KEY_TITLE)
//        recyclerItems.add(RecyclerItem(song, artist, album, m.embeddedPicture,"android.resource://ru.spb.designedBy239School.advancedMusicPlayer/raw/heavy"))
//
//        m.setDataSource(this, Uri.parse("android.resource://ru.spb.designedBy239School.advancedMusicPlayer/raw/sugar"))
//        artist ="artist: "+ m.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ARTIST)
//        album =""+ m.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ALBUM)
//        song = ""+m.extractMetadata(MediaMetadataRetriever.METADATA_KEY_TITLE)
//        recyclerItems.add(RecyclerItem(song, artist, album, m.embeddedPicture,"android.resource://ru.spb.designedBy239School.advancedMusicPlayer/raw/heavy"))
//
//        m.setDataSource(this, Uri.parse("android.resource://ru.spb.designedBy239School.advancedMusicPlayer/raw/ne_s_nachala_oxxymiron"))
//        artist ="artist: "+ m.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ARTIST)
//        album =""+ m.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ALBUM)
//        song = ""+m.extractMetadata(MediaMetadataRetriever.METADATA_KEY_TITLE)
//        recyclerItems.add(RecyclerItem(song, artist, album, m.embeddedPicture,"android.resource://ru.spb.designedBy239School.advancedMusicPlayer/raw/heavy"))