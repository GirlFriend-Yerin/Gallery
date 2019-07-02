package gfriend_yerin.textblurgallery.view.Gallery

import gfriend_yerin.textblurgallery.data.GalleryDataSource

class GalleryPresenter(private val dataSource: GalleryDataSource) : GalleryContract.Presenter {

    private var view : GalleryContract.View? = null
    private var count : Int = 0

    override fun setView(view: GalleryContract.View) {
        this.view = view
    }

    override fun initView(){
        view!!.initAdapter(dataSource.loadGallery())
        view!!.updateCountUI(count)
    }

    override fun countUp(){
        view!!.updateCountUI(++count)
    }

    override fun countDown(){
        view!!.updateCountUI(--count)
    }
}