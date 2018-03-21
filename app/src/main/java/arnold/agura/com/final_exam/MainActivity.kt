package arnold.agura.com.final_exam

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock.EXTRA_MESSAGE
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import android.view.View
import arnold.agura.com.final_exam.Adapter.AlbumAdapter
import arnold.agura.com.final_exam.Model.Album
import arnold.agura.com.final_exam.Model.AlbumDetails
import arnold.agura.com.final_exam.Model.AlbumMatches
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

        search.setOnQueryTextListener(object : android.widget.SearchView.OnQueryTextListener {

            override fun onQueryTextChange(newText: String): Boolean {

                return false
            }

            override fun onQueryTextSubmit(query: String): Boolean {
                filter(query)
                progressBar.visibility = View.VISIBLE
                return false
            }

        })


    }
    private fun filter(text: String) {
        val albumList = ArrayList<AlbumDetails>()
        var limit:Int = 50
        var albumName:String = text
        var url = "http://ws.audioscrobbler.com/2.0/?method=album.search&album=$albumName&api_key=cd228ef0b2462f130c7bf249038072df&limit=$limit&format=json"
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
                    textView.visibility = View.GONE
                    progressBar.visibility = View.GONE
                    for(album in album.results.albummatches.album){
                        albumList.add(album)
                    }
                    println(album.results.albummatches.album)

                    val adapterStat = AlbumAdapter(this@MainActivity, albumList)
                    val layoutManagerStat = LinearLayoutManager(this@MainActivity)

                    recyclerView.adapter = adapterStat
                    recyclerView.layoutManager = layoutManagerStat
                }
            }

        })



    }
    override fun onCreateOptionsMenu(menu: Menu?):Boolean{
        menuInflater.inflate(R.menu.menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){
            R.id.clear ->{
                search.setQuery("",false)
            }
        }

        return super.onOptionsItemSelected(item)
    }
}

