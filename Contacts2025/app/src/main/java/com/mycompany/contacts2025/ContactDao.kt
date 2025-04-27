package com.mycompany.contacts2025

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ContactDao {
    @Insert suspend fun insertContact(contact: Contact)
    @Query("DELETE FROM contacts WHERE contactId = :id") suspend fun deleteById(id: Int)

    @Query("SELECT * FROM contacts")
    fun getAll(): LiveData<List<Contact>>

    @Query("SELECT * FROM contacts WHERE contactName LIKE '%'||:name||'%'")
    suspend fun findByName(name: String): List<Contact>

    @Query("SELECT * FROM contacts ORDER BY contactName ASC")
    fun getAllAsc(): LiveData<List<Contact>>

    @Query("SELECT * FROM contacts ORDER BY contactName DESC")
    fun getAllDesc(): LiveData<List<Contact>>
}
