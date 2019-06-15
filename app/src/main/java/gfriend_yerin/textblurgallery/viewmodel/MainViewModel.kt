package gfriend_yerin.textblurgallery.viewmodel;

import androidx.lifecycle.ViewModel
import gfriend_yerin.textblurgallery.view.MainActivityViewNavigator

class MainViewModel(private val navigator : MainActivityViewNavigator) : ViewModel(){

    fun gotoGallery(){
        navigator.gotoGallery()
    }
}
