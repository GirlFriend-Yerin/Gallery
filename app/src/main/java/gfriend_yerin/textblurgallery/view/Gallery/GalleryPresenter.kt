package gfriend_yerin.textblurgallery.view.Gallery

import gfriend_yerin.textblurgallery.data.GalleryDataSource
import gfriend_yerin.textblurgallery.data.obj.PhotoVO

class GalleryPresenter(private val dataSource: GalleryDataSource) : GalleryContract.Presenter {

    private var view : GalleryContract.View? = null
    private var galleryItem : ArrayList<PhotoVO>? = null
    private val selectedList = ArrayList<String>()
    private var count : Int = 0

    override fun setView(view: GalleryContract.View) {
        this.view = view
    }

    override fun initView(){
        galleryItem = dataSource.loadGallery()
        view!!.initAdapter(galleryItem!!)
        view!!.updateCountUI(count)
    }

    override fun countUp(){
        view!!.updateCountUI(++count)
    }

    override fun countDown(){
        view!!.updateCountUI(--count)
    }

    override fun addItem(pos: Int) {
        selectedList.add(galleryItem!![pos].imagePath)
    }

    override fun removeItem(pos : Int) {
        selectedList.remove(galleryItem!![pos].imagePath)
    }

    override fun getSelectedList(): ArrayList<String> {
        return selectedList
    }
}