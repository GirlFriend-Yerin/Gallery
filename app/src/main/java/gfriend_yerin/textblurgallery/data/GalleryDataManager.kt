package gfriend_yerin.textblurgallery.data

import android.content.Context
import android.net.Uri
import android.provider.MediaStore
import gfriend_yerin.textblurgallery.data.obj.PhotoVO

class GalleryDataManager(private val context : Context) : GalleryDataSource{

    companion object {
        @Volatile
        private var INSTANCE : GalleryDataSource? = null

        fun getInstance(context : Context) : GalleryDataSource {
            return INSTANCE ?: GalleryDataManager(context).apply {
                INSTANCE = this
            }

        }
    }

    override fun loadGallery() : ArrayList<PhotoVO>{

        val photoList : ArrayList<PhotoVO> = ArrayList()
        val uri : Uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        val projection = arrayOf(MediaStore.MediaColumns.DATA, MediaStore.Images.Media.DATA, MediaStore.Images.Media.DATE_ADDED)

        val cursor = context.contentResolver.query(uri, projection, null, null, null)!!

        val columnIndexData = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA)

        cursor.moveToLast()

        while (cursor.moveToPrevious()){
            photoList.add( PhotoVO(cursor.getString(columnIndexData), false))
        }

        cursor.close()

        return photoList
    }
}