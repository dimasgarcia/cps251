package com.mycompany.contacts2025

import android.app.Application
import androidx.lifecycle.*
import kotlinx.coroutines.launch

class MainViewModel(app: Application) : AndroidViewModel(app) {
    private val dao = ContactRoomDatabase
        .getDatabase(app)
        .contactDao()
    private val repo = ContactRepository(dao)

    /** Expose the “master” LiveData of all contacts */
    val allContacts: LiveData<List<Contact>> = repo.allContacts

    /** Expose search results */
    private val _searchResults = MutableLiveData<List<Contact>>()
    val searchResults: LiveData<List<Contact>> = _searchResults

    /** Insert, delete and find run in coroutines */
    fun insert(name: String, phone: String) = viewModelScope.launch {
        repo.insertContact(Contact(contactName = name, contactPhone = phone))
    }

    fun delete(id: Int) = viewModelScope.launch {
        repo.deleteById(id)
    }

    fun find(name: String) = viewModelScope.launch {
        _searchResults.value = repo.findByName(name)
    }

    // Sorting just switches which LiveData the UI observes
    fun sortAsc(): LiveData<List<Contact>> = repo.getAllAsc()
    fun sortDesc(): LiveData<List<Contact>> = repo.getAllDesc()
}
