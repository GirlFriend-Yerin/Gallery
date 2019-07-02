package gfriend_yerin.textblurgallery.view

import gfriend_yerin.textblurgallery.view.Gallery.GalleryAdapter

@FunctionalInterface
interface OnItemClickListener {
    fun OnItemClick(photoViewHolder: GalleryAdapter.PhotoViewHolder, position : Int)
}