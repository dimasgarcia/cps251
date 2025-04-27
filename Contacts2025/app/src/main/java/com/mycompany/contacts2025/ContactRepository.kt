package com.mycompany.contacts2025

import androidx.lifecycle.LiveData

class ContactRepository(private val dao: ContactDao) {

    // 1) The “master” LiveData stream of all contacts
    val allContacts: LiveData<List<Contact>> = dao.getAll()

    // 2) Ascending / descending streams
    fun getAllAsc(): LiveData<List<Contact>>  = dao.getAllAsc()
    fun getAllDesc(): LiveData<List<Contact>> = dao.getAllDesc()

    // 3) Here are the suspend‐functions for the ViewModel to call
    suspend fun insertContact(contact: Contact) {
        dao.insertContact(contact)
    }

    suspend fun deleteById(id: Int) {
        dao.deleteById(id)
    }

    suspend fun findByName(name: String): List<Contact> {
        return dao.findByName(name)
    }
}
