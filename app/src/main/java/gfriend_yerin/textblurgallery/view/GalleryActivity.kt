package gfriend_yerin.textblurgallery.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import gfriend_yerin.textblurgallery.R
import gfriend_yerin.textblurgallery.data.GalleryDataManager
import gfriend_yerin.textblurgallery.databinding.ActivityGalleryBinding
import gfriend_yerin.textblurgallery.viewmodel.GalleryViewModel
import kotlinx.android.synthetic.main.activity_gallery.*

class GalleryActivity : AppCompatActivity() {

    private val mContext = this
    private lateinit var binding: ActivityGalleryBinding
    private lateinit var viewModel: GalleryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_gallery)

        gallery_recycler.apply {
            adapter = GalleryAdapter(mContext, R.layout.gallery_item)
            layoutManager = GridLayoutManager(mContext, 3)
            itemAnimator = DefaultItemAnimator()
            addItemDecoration(GridDividerDecoration(mContext, R.drawable.gallery_item_divider))
        }


        initView()
    }

    fun initView() {
        viewModel = GalleryViewModel(GalleryDataManager.getInstance(this))
        binding.viewholder = viewModel

        viewModel.loadGallery()
    }
}
