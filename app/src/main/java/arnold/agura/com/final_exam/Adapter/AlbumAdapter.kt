package arnold.agura.com.final_exam.Adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.content.Context
import android.view.ViewGroup
import arnold.agura.com.final_exam.Model.Album
import arnold.agura.com.final_exam.Model.AlbumDetails
import arnold.agura.com.final_exam.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.album.view.*

/**
 * Created by Arnold on 21 Mar 2018.
 */
class AlbumAdapter(val mContext: Context,val albumList: ArrayList<Album>): RecyclerView.Adapter<AlbumViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {
        val layoutInflater = LayoutInflater.from(parent?.context)
        val cellForRow = layoutInflater.inflate(R.layout.album, parent, false)
        return AlbumViewHolder(cellForRow)
    }

    override fun getItemCount(): Int {
       return albumList.size
    }

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        val album: Album = albumList[position]
//        holder?.view?.txtAlbumName?.text = album.albumDetails.name
//        holder?.view?.txtAlbumArtist?.text = album.albumDetails.artist

        val albumImage = holder?.view?.imgAlbum
//        Picasso.with(holder?.view?.context).load(album.image).into(albumImage)
    }
}
class AlbumViewHolder (val view: View): RecyclerView.ViewHolder(view){

}