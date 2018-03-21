package arnold.agura.com.final_exam

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock.EXTRA_MESSAGE
import arnold.agura.com.final_exam.Adapter.AlbumAdapter
import arnold.agura.com.final_exam.Model.Album
import arnold.agura.com.final_exam.Model.AlbumDetails
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Response
import java.io.IOException
import java.util.Locale.filter

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var albumName:String = "Believe"
        var url =  "https://ws.audioscrobbler.com/2.0/?method=album.getinfo&api_key=cd228ef0b2462f130c7bf249038072df&artist=Cher&album=believe&format=json"
            var request = okhttp3.Request.Builder().url(url).build()
            var client = OkHttpClient()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call?, e: IOException?) {

            }
            override fun onResponse(call: Call?, response: Response?) {
                val json = response?.body()?.string()
                val gson = GsonBuilder().create()
                val album = gson.fromJson(json, Album::class.java)
                runOnUiThread{
                    println(album)
                }
            }

        })
        search.setOnQueryTextListener(object : android.widget.SearchView.OnQueryTextListener {

            override fun onQueryTextChange(newText: String): Boolean {
                filter(newText.toString())
                return false
            }

            override fun onQueryTextSubmit(query: String): Boolean {
                filter(query)
                return false
            }

        })


    }
    private fun filter(text: String) {
        val filterdNames = ArrayList<Album>()

        for (s in filterdNames) {
            if (s.albumDetails.name.toLowerCase().contains(text.toLowerCase())) {
                filterdNames.add(s)
            }

            recyclerView.adapter = AlbumAdapter(this@MainActivity, filterdNames)
        }

    }

}
