package ru.spb.designedBy239School.advancedMusicPlayer

import android.Manifest
import android.content.pm.PackageManager
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File

class MainActivity : AppCompatActivity() {


    fun getOurPlayList(inputFile: File): ArrayList<File>{
        val filelist : ArrayList<File> = ArrayList()
        for (file in inputFile.listFiles()){
            if(file.name.endsWith(".mp3")){
                filelist.add(file)
            }
        }
        return filelist
    }

    val REQUEST_CONSTANT=1
    fun getPlayList(inputFile : File) : ArrayList<HashMap<String,String>> {

        val filelist : ArrayList<HashMap<String,String>> = ArrayList()
        for (file in inputFile.listFiles()){
            if(file.name.endsWith(".mp3")){

            }
        }

        try {
            val files = inputFile.listFiles() //here you will get NPE if directory doesn't contains any file,handle it like this.
            for (file in files) {
                if (file.isDirectory()) {

                    if (getPlayList(file) != null) {
                        filelist.addAll(getPlayList(file))
                    } else {
                        break
                    }
                } else if (file.getName().endsWith(".mp3")) {
                    val song = HashMap<String, String>()
                    Log.d("MYMUSIC",song.put("file_path", file.getAbsolutePath()))
                    song.put("file_name", file.getName())
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

        var list = Environment.getExternalStorageDirectory().list()
        var list2 = Environment.getExternalStorageDirectory().listFiles().get(2).list()
        var list3 = Environment.getExternalStorageDirectory().listFiles().get(2)
        Log.d("MYMUSIC", "is list3 empty? "+list3.list().isEmpty().toString())
        var listMusic =getOurPlayList(list3)

        val player = MediaPlayer.create(applicationContext,listMusic[0].toUri())
        Play.setOnClickListener {
            player.start()
        }

        Pause.setOnClickListener{
            player.pause()
        }
//        Log.d("MYMUSIC", "is play list empty? "+listMusic.isEmpty().toString())
//        for (i in list) {
//            Log.d("MYMUSIC", i.toString())
//        }
//        Log.d("MYMUSIC", "!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!Next path")
//        for (i in list2) {
//            Log.d("MYMUSIC", i.toString())
//        }
    }

}
