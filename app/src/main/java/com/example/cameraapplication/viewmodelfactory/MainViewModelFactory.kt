package com.example.cameraapplication.viewmodelfactory

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.cameraapplication.main.MainViewModel


class MainViewModelFactory(private val mApplication: Context) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MainViewModel(mApplication) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}