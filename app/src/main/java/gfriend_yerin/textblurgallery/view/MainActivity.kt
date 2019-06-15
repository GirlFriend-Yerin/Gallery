package gfriend_yerin.textblurgallery.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import gfriend_yerin.textblurgallery.R
import gfriend_yerin.textblurgallery.databinding.ActivityMainBinding
import gfriend_yerin.textblurgallery.viewmodel.MainViewModel

class MainActivity : AppCompatActivity(), MainActivityViewNavigator {

    private lateinit var mainViewModel : MainViewModel
    override fun gotoGallery(){
        intent = Intent(this, GalleryActivity::class.java)
        startActivity(intent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding : ActivityMainBinding = DataBindingUtil.setContentView(this,
            R.layout.activity_main
        )

        mainViewModel = MainViewModel(this)
        binding.viewModel = mainViewModel

    }

}
