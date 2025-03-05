package com.mycompany.annnamesavedata1viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    // Uses a backing property to expose an immutable LiveData to the UI.
    private val _names = MutableLiveData<List<String>>(emptyList())
    val names: LiveData<List<String>> get() = _names

    // The function to add a name to the list.
    fun addName(newName: String) {
        // Only add non-blank names.
        if (newName.isNotBlank()) {
            val updatedNames = _names.value.orEmpty().toMutableList()
            updatedNames.add(newName)
            _names.value = updatedNames
        }
    }
}
