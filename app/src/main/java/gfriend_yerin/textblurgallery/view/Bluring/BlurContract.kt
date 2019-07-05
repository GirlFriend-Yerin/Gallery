package gfriend_yerin.textblurgallery.view.Bluring

import android.content.Context

interface BlurContract {
    interface View{
        fun updatePage(pos : Int, max : Int)
        fun updateAdapter(list : ArrayList<String>)

        fun showRecognizeText(list : ArrayList<String>)
    }

    interface Presenter{
        fun setView(view : View)

        fun initPageView(list : ArrayList<String>)
        fun movePage(pos : Int)

        fun recognizeText(context : Context, pos : Int)
        fun displayRecognizeList()
    }
}