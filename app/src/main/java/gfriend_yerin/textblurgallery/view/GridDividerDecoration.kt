package gfriend_yerin.textblurgallery.view

import android.content.Context
import android.content.res.Resources
import android.graphics.Canvas
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by woong on 2015. 9. 15..
 */

class GridDividerDecoration() : RecyclerView.ItemDecoration() {

    private lateinit var mDivider: Drawable
    private val mInsets = 1

    constructor(context: Context, layout: Int) : this() {
        mDivider = ContextCompat.getDrawable(context, layout)!!
    }

    override fun onDrawOver(c: Canvas, parent: RecyclerView) {
        drawVertical(c, parent)
        drawHorizontal(c, parent)
    }

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        outRect.set(mInsets, mInsets, mInsets, mInsets);
    }

    private fun drawVertical(c: Canvas, parent: RecyclerView) {
        if (parent.childCount == 0) return

        val left = parent.paddingLeft
        val right = parent.width - parent.paddingRight

        val child = parent.getChildAt(0)
        if (child.height == 0) return

        val params = child.layoutParams as RecyclerView.LayoutParams
        var top = child.bottom + params.bottomMargin + mInsets
        var bottom = top + mDivider.getIntrinsicHeight()

        val parentBottom = parent.height - parent.paddingBottom
        while (bottom < parentBottom) {
            mDivider.setBounds(left, top, right, bottom)
            mDivider.draw(c)

            top += mInsets + params.topMargin + child.height + params.bottomMargin + mInsets
            bottom = top + mDivider.getIntrinsicHeight()
        }
    }

    private fun drawHorizontal(c: Canvas, parent: RecyclerView) {
        val top = parent.paddingTop
        val bottom = parent.height - parent.paddingBottom

        val childCount = parent.childCount
        for (i in 0 until childCount) {
            val child = parent.getChildAt(i)
            val params = child.layoutParams as RecyclerView.LayoutParams
            val left = child.right + params.rightMargin + mInsets
            val right = left + mDivider.getIntrinsicWidth()
            mDivider.setBounds(left, top, right, bottom)
            mDivider.draw(c)
        }
    }
}