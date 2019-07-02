package gfriend_yerin.textblurgallery.view.Gallery

import gfriend_yerin.textblurgallery.data.obj.PhotoVO

interface GalleryContract {
    interface View{
        fun updateCountUI(cnt : Int)

        fun initAdapter(list : ArrayList<PhotoVO>)
        fun updateAdapter()
        fun updateAdapter(pos : Int)

    }

    interface Presenter{
        fun setView(view : GalleryContract.View)

        fun loadPhotoList()
        fun countUp()
        fun countDown()
    }
}