package gfriend_yerin.textblurgallery.view.Bluring;

import android.content.Context
import gfriend_yerin.textblurgallery.util.TextRecogUtils

class BlurPresenter : BlurContract.Presenter {
    private var pos : Int = 1
    private var itemList : ArrayList<String>? = null
    private var resultList : ArrayList<String>? = null
    private lateinit var view : BlurContract.View

    override fun setView(view: BlurContract.View) {
        this.view = view
    }

    override fun initPageView(list: ArrayList<String>) {
        this.itemList = list

        view.updateAdapter(list)
        view.updatePage(pos, itemList!!.size)
    }

    override fun movePage(pos: Int) {
        this.pos = pos
        view.updatePage(this.pos, itemList!!.size)
    }

    override fun recognizeText(context : Context, pos: Int){
        resultList = TextRecogUtils.findText(context, itemList!![pos], "")
    }

    override fun displayRecognizeList() {

        if (resultList != null)
            view.showRecognizeText(resultList!!)
    }
}
