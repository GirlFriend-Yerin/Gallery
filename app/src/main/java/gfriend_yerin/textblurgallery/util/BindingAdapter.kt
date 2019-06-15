package gfriend_yerin.textblurgallery.util

import android.util.Log
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import gfriend_yerin.textblurgallery.data.ValueObject.PhotoVO
import gfriend_yerin.textblurgallery.view.GalleryAdapter
import gfriend_yerin.textblurgallery.view.OnItemClickListener

object BindingAdapter {

    @JvmStatic
    @BindingAdapter("bind_item")
    fun setImage(view: RecyclerView, item: MutableList<PhotoVO>) {
        val adapter = view.adapter
        if (adapter != null) {
            (adapter as GalleryAdapter).apply {
                setPhotoList(item)
                setOnItemClickListener(object : OnItemClickListener {
                    override fun OnItemClick(photoViewHolder: GalleryAdapter.PhotoViewHolder, position: Int) {
                        val photo = item[position]

                        item[position].selected = !photo.selected
                        adapter.notifyItemChanged(position)
                    }
                })
                notifyDataSetChanged()
            }
        }
    }

    @JvmStatic
    @BindingAdapter("bind_image")
    fun loadImage(view: ImageView, url: String) {

    }

}