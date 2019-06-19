package ru.spb.designedBy239School.advancedMusicPlayer

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File

class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    private val REQUEST_CONSTANT = 1
    private  var mediaPlayer = MediaPlayer()
    private var listOfMusicNames: ArrayList<String> = ArrayList()

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

        for (i in listMusic){
            listOfMusicNames.add(i["name"].toString())
        }
        MainListView.adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listOfMusicNames)
        MainListView.setOnItemClickListener { _ , item_Clicked, position, _ ->
            (item_Clicked as TextView).text = item_Clicked.text.toString() + " ...playing"
            Intent(this, PlayerActivity::class.java).putExtra(
                "data_id",
                item_Clicked.text.toString()
            )
            mediaPlayer.stop()
            mediaPlayer.reset()
            mediaPlayer.setDataSource(this, File(listMusic[position]["fullName"]).toUri())
            Log.d("MUSICLIST", listMusic[position]["fullName"])
            mediaPlayer.prepare()
            mediaPlayer.start()

        }
        Pause.setOnClickListener {
            mediaPlayer.pause()
        }
        Play.setOnClickListener {
            mediaPlayer.start()
        }

        ArrayAdapter.createFromResource(
            this,
            R.array.sorts,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            settings_spinner.adapter = adapter
        }

        settings_spinner.onItemSelectedListener = this

        TODO() // add searchView
//        if (search.is)

    }



    override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
        Log.d("SPINNER", parent.getItemAtPosition(position).toString())
        if (parent.getItemAtPosition(position).toString() == "Sort by artist") {
            listOfMusicNames.sort()
            Log.d("SPINNER", "Its sort by artist")
            MainListView.adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listOfMusicNames)
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>) {
        // Another interface callback
    }

}
