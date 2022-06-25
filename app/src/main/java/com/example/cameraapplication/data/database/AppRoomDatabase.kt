package com.example.cameraapplication.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.cameraapplication.data.database.schemas.ImageSchema

@Database(
    entities = [ImageSchema::class],
    version = 1,
    exportSchema = true
)
abstract class AppRoomDatabase : RoomDatabase() {
    public abstract fun appDataService(): AppDataService

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: AppRoomDatabase? = null

        fun getDatabase(context: Context): AppRoomDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppRoomDatabase::class.java,
                    "app_room_data_base"
                ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }


    }

}