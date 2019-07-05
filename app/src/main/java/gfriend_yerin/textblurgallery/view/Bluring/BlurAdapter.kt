package gfriend_yerin.textblurgallery.view.Bluring

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.blur_item.view.*

class BlurAdapter(private val context : Context,
                  private val layoutId : Int,
                  private val items : ArrayList<String>) : PagerAdapter() {

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val layout = LayoutInflater.from(container.context).inflate(layoutId, container, false)

        Glide.with(layout.context).load(items[position]).dontAnimate().into(layout.blur_pager_image)
        container.addView(layout)

        return layout
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun getCount(): Int {
        return items.size
    }
}