package ru.spb.designedBy239School.advancedMusicPlayer

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.media.MediaMetadataRetriever
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toDrawable
import androidx.core.net.toUri
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File

class MainActivity : AppCompatActivity() {

    private val REQUEST_CONSTANT=1
    private  var mediaPlayer = MediaPlayer()

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),  REQUEST_CONSTANT)
        }
        Equalizer_button.setOnClickListener {
            val intent = Intent(this, EqualizerActivity::class.java)
            intent.putExtra("Session_Id", mediaPlayer.audioSessionId.toString())
            startActivity(intent)

        }

        var listMusic = getPlayListStrings(Environment.getExternalStorageDirectory())
        Log.d("MUSICLIST","is empty? "+ listMusic.isEmpty().toString())
        ToPlayListActivity.setOnClickListener {
            val intent = Intent(this, PlaylistActivity::class.java)
            startActivity(intent)
        }
        ToPlayerActivity.setOnClickListener {

        }
        val listOfMyMusic: ArrayList<String> = ArrayList()
        for (i in listMusic){
            listOfMyMusic.add(i["name"].toString())
        }
        var recyclerView: RecyclerView = findViewById(R.id.recycler_view)
        recyclerView.layoutManager= LinearLayoutManager(this)

        val recyclerItems:ArrayList<RecyclerItem> = ArrayList()
        for (i in listMusic){
            var m = MediaMetadataRetriever()
            m.setDataSource(i["path"])
            var image = m.getEmbeddedPicture()
            if (image!=null){

            }
            var artist = m.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ARTIST)
            var album = m.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ALBUM)
            if (artist==null)    artist=""
            if (album==null)     album=""
            var imageView: ImageView =ImageView(this)
            imageView.setImageResource(R.drawable.default_song_picture)

            recyclerItems.add(RecyclerItem(i["name"].toString(), artist, album, R.drawable.default_song_picture))
        }
        val adapterR = MyRecyclerViewAdapter(recyclerItems, this)
        recyclerView.adapter= adapterR

        Pause.setOnClickListener {
            mediaPlayer.pause()
        }
        Play.setOnClickListener {
            mediaPlayer.start()
        }
    }

}
