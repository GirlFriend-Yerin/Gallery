package gfriend_yerin.textblurgallery.view.Gallery

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import gfriend_yerin.textblurgallery.data.obj.PhotoVO
import gfriend_yerin.textblurgallery.view.OnItemClickListener
import kotlinx.android.synthetic.main.gallery_item.view.*

class GalleryAdapter(
    private val mContext: Context,
    private val layout: Int,
    private val photoList: ArrayList<PhotoVO>
) : RecyclerView.Adapter<GalleryAdapter.PhotoViewHolder>() {

    private lateinit var itemClickListener: OnItemClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(layout, parent, false )
        return PhotoViewHolder(view)
    }

    override fun getItemCount() = photoList.size

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) = holder.bind(photoList[position], position)

    inner class PhotoViewHolder(private val view : View) : RecyclerView.ViewHolder(view) {
        fun bind(item: PhotoVO, position: Int) {

            Glide.with(view).load(item.imagePath).into(view.gallery_photo_item)
        }
    }
}