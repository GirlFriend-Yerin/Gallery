package gfriend_yerin.textblurgallery.data

import gfriend_yerin.textblurgallery.data.obj.PhotoVO

interface GalleryDataSource {

    fun loadGallery() : ArrayList<PhotoVO>
}