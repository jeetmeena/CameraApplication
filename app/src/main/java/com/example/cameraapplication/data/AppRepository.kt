package com.example.cameraapplication.data

import androidx.lifecycle.LiveData
import com.example.cameraapplication.data.database.AppDataService
import com.example.cameraapplication.data.database.schemas.ImageSchema

class AppRepository(private val appDataService: AppDataService) {

    fun getImageOfAlbumName(albumName: String): List<ImageSchema> {
        return appDataService.getImagesOfAlbum(albumName)
    }

    fun getAllImages(): LiveData<List<ImageSchema>> {
        return appDataService.getImages()
    }

    fun getAlbumNames(): LiveData<List<String>> {
        return appDataService.getAlbumNames()
    }

    fun getLastImage():LiveData<ImageSchema>{
       return appDataService.getLastRowImage()
    }

    suspend fun insertImage(image: ImageSchema) {
        appDataService.insertImage(image)
    }
}