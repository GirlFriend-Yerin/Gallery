package gfriend_yerin.textblurgallery.view

@FunctionalInterface
interface OnItemClickListener {
    fun OnItemClick(photoViewHolder: GalleryAdapter.PhotoViewHolder, position : Int)
}