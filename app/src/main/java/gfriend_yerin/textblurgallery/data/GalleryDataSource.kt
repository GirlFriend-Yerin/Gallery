package gfriend_yerin.textblurgallery.data

import gfriend_yerin.textblurgallery.data.ValueObject.PhotoVO

interface GalleryDataSource {

    fun loadGallery() : List<PhotoVO>
}