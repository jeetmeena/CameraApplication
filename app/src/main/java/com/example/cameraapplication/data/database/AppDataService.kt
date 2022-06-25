package com.example.cameraapplication.data.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.cameraapplication.data.database.schemas.ImageSchema
import org.jetbrains.annotations.NotNull


@Dao
interface AppDataService {
    //VIEW PRODUCT From SEARCH
    @Query("SELECT * FROM image_schema ORDER BY timestamp DESC")
    fun getImages(): LiveData<List<ImageSchema>>

    @Query("SELECT * FROM image_schema WHERE albumName = :albumName")
    fun getImagesOfAlbum(@NotNull albumName: String): List<ImageSchema>

    @Query("SELECT DISTINCT albumName FROM image_schema")
    fun getAlbumNames(): LiveData<List<String>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertImage(@NotNull recentViewProduct: ImageSchema)

    @Query("DELETE FROM image_schema WHERE albumName = :albumName")
    fun deleteAlbumImage(@NotNull albumName: String)


    /*@Query("DELETE FROM image_schema WHERE sku IN (SELECT sku FROM recent_view_products LIMIT 1);")
    fun deleteLastRowOfPFSSku()*/

    @Query("SELECT * FROM image_schema WHERE timestamp IN (SELECT timestamp FROM image_schema ORDER BY timestamp DESC LIMIT 1);\n")
    fun getLastRowImage():LiveData<ImageSchema>

    @Query("DELETE FROM image_schema")
    fun deleteAllImage()

    @Query("SELECT COUNT('timestamp') FROM image_schema")
    fun getImageCount(): Int

    @Transaction
    suspend fun updateImageData(name: String) {

    }
}