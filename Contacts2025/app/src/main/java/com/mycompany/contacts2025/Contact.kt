package com.mycompany.contacts2025

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "contacts")
data class Contact(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "contactId") val contactId: Int = 0,
    @ColumnInfo(name = "contactName") val contactName: String,
    @ColumnInfo(name = "contactPhone") val contactPhone: String
)
