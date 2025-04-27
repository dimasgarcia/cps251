package com.mycompany.contacts2025

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Contact::class], version = 1, exportSchema = false)
abstract class ContactRoomDatabase : RoomDatabase() {
    abstract fun contactDao(): ContactDao

    companion object {
        @Volatile private var INSTANCE: ContactRoomDatabase? = null

        fun getDatabase(ctx: Context): ContactRoomDatabase =
            INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    ctx.applicationContext,
                    ContactRoomDatabase::class.java,
                    "contact_database"
                ).build().also { INSTANCE = it }
            }
    }
}
