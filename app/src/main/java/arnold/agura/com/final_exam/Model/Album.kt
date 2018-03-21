package arnold.agura.com.final_exam.Model

import com.google.gson.annotations.SerializedName
import android.media.Image

/**
 * Created by Arnold on 21 Mar 2018.
 */

data class Album(
        var albumDetails: AlbumDetails
)
data class AlbumDetails(
        val name: String,
        val artist: String
)
data class AlbumImg(
        @SerializedName("#text") val text: String,
        val size: String
)