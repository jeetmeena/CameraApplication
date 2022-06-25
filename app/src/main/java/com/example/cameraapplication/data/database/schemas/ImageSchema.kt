package com.example.cameraapplication.data.database.schemas

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "image_schema")
data class ImageSchema(
    @PrimaryKey  val timestamp: Long,
    val name: String,
    val albumName : String)
