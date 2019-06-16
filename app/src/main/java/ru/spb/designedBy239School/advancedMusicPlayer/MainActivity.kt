package ru.spb.designedBy239School.advancedMusicPlayer

import android.Manifest
import android.content.pm.PackageManager
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.widget.ArrayAdapter
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File

class MainActivity : AppCompatActivity() {



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
                    song["fullName"] = file.absolutePath
                    filelist.add(song)
                }
            }
            return filelist
        } catch (e: Exception) {
            return ArrayList()
        }
    }

    private val REQUEST_CONSTANT=1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                REQUEST_CONSTANT
            )
        }

        //var musicList = getPlayListStrings(Environment.getExternalStorageDirectory())
        var musicList : ArrayList<HashMap<String,String>> = ArrayList()
        for (i in 0 until 10){
            val map: HashMap<String, String> = hashMapOf("name" to "Oxxxymiron: trek ${i+1}")
            musicList.add(map)
        }
        Log.d("MYMUSIC","Play list is ready")
        val nameOfMusicList :ArrayList<String> = ArrayList()
        var k = 0
        for (i in musicList){
            nameOfMusicList.add(i["name"].toString())
            Log.d("MYMUSIC",nameOfMusicList[k])
            k++
        }
        val recyclerView: RecyclerView = findViewById(androidx.recyclerview.widget.RecyclerView.generateViewId())
        recyclerView.layoutManager = LinearLayoutManager(this)

    }

}
