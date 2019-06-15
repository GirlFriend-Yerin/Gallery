package gfriend_yerin.textblurgallery.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import gfriend_yerin.textblurgallery.R
import gfriend_yerin.textblurgallery.data.GalleryDataManager
import gfriend_yerin.textblurgallery.databinding.ActivityGalleryBinding
import gfriend_yerin.textblurgallery.viewmodel.GalleryViewModel

class GalleryActivity : AppCompatActivity() {

    private lateinit var binding : ActivityGalleryBinding
    private lateinit var viewModel : GalleryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_gallery)

        initView()
    }

    fun initView(){
        viewModel = GalleryViewModel(GalleryDataManager.getInstance(this))
        binding.viewholder = viewModel

        viewModel.loadGallery()
    }
}
