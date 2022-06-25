package com.example.cameraapplication.main

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cameraapplication.data.AppRepository
import com.example.cameraapplication.data.database.AppRoomDatabase
import com.example.cameraapplication.data.database.schemas.ImageSchema
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(mApplication: Context) : ViewModel() {
    // on below line we are initializing
    // our dao, repository and all notes
    val repository: AppRepository
    var albumNames: LiveData<List<String>>
    var albumImages = MutableLiveData<List<ImageSchema>>()
    init {
        val dao = AppRoomDatabase.getDatabase(mApplication).appDataService()
        repository = AppRepository(dao)
        albumNames = repository.getAlbumNames()
    }

    fun getAllImages(): LiveData<List<ImageSchema>> {
        return repository.getAllImages()
    }

    fun getImagesByAlbumName(albumName: String) {
        viewModelScope.launch(Dispatchers.IO) {
            albumImages.postValue(repository.getImageOfAlbumName(albumName))
         }
    }

    fun insertImage(imageSchema: ImageSchema) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertImage(imageSchema)
        }
    }

    fun getLastImage(): LiveData<ImageSchema> {
        return repository.getLastImage()
    }
    fun clearAlbumImages(){
        albumImages.postValue(emptyList())
    }
}