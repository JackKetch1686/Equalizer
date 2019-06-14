package ru.spb.designedBy239School.advancedMusicPlayer


import android.Manifest
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import java.io.File
import android.R.attr.keySet
import android.media.MediaPlayer
import android.os.Environment
import android.util.Log


class MainActivity : AppCompatActivity() {
    fun getPlayList(rootPath : String) : ArrayList<HashMap<String,String>> {

        val filelist : ArrayList<HashMap<String,String>> = ArrayList()
        try {
            val rootFolder = File(rootPath)
            val files =
                rootFolder.listFiles() //here you will get NPE if directory doesn't contains any file,handle it like this.
            for (file in files) {
                if (file.isDirectory()) {
                    if (getPlayList(file.getAbsolutePath()) != null) {
                        filelist.addAll(getPlayList(file.getAbsolutePath()))
                    } else {
                        break
                    }
                } else if (file.getName().endsWith(".mp3")) {
                    val song = HashMap<String, String>()
                    song.put("file_path", file.getAbsolutePath())
                   // song.put("file_name", file.getName())
                    filelist.add(song)
                }
            }
            return filelist
        } catch (e: Exception) {
            return ArrayList()
        }
    }


    var REQUEST_CONSTANT: Int =1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), REQUEST_CONSTANT)
        }

        Log.d("MYLIST",this.getFileStreamPath("").absolutePath)



       var musicList :ArrayList<HashMap<String,String>> =getPlayList(this.getFileStreamPath("").absolutePath)
        musicList.isEmpty()
        Log.d("MYLIST","list is empty& "+musicList.isEmpty().toString())

        for (i in musicList   ){
            Log.d("MYLIST",i.keys.toString())
        }
    }
}
