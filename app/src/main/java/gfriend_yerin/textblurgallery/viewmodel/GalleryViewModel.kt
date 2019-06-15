package gfriend_yerin.textblurgallery.viewmodel

import androidx.databinding.ObservableArrayList
import androidx.lifecycle.ViewModel
import gfriend_yerin.textblurgallery.data.GalleryDataSource
import gfriend_yerin.textblurgallery.data.ValueObject.PhotoVO

class GalleryViewModel(private val dataSource : GalleryDataSource) : ViewModel(){

    val photoList = ObservableArrayList<PhotoVO>()

    fun loadGallery(){
        photoList.addAll(dataSource.loadGallery())
    }
}