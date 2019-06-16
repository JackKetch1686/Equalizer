package ru.spb.designedBy239School.advancedMusicPlayer

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Environment
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File

class MainActivity : AppCompatActivity() {

    private val REQUEST_CONSTANT=1
    private  var player: MediaPlayer = MediaPlayer()

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

        var listMusic = getPlayListStrings(Environment.getExternalStorageDirectory())

        ToPlayListActivity.setOnClickListener {
            val intent = Intent(this, PlaylistActivity::class.java)
            startActivity(intent)
        }
        ToPlayerActivity.setOnClickListener {
            val intent = Intent(this, PlayerActivity::class.java)
            startActivity(intent)
        }
        val listOfMyMusic: ArrayList<String> = ArrayList()
        for (i in listMusic){
            listOfMyMusic.add(i["name"].toString())
        }

        MainListView.adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listOfMyMusic)

        MainListView.setOnItemClickListener { _ , item_Clicked, position, _ ->
            (item_Clicked as TextView).text = item_Clicked.text.toString() + " ...playing"
            Intent(this, PlayerActivity::class.java).putExtra(
                "data_id",
                item_Clicked.text.toString()
            )
            player.setDataSource(applicationContext, File(listMusic[position]["fullName"]).toUri())
            player.start()
        }
        pause.setOnClickListener {
            player.pause()
        }



    }

}
