package arnold.agura.com.final_exam.Model

import com.google.gson.annotations.SerializedName
import android.media.Image

/**
 * Created by Arnold on 21 Mar 2018.
 */
data class Album(
        val name: String,
        val artist: String,
        val image : AlbumImg
)
data class AlbumImg(
        val text: String,
        val size: String
)