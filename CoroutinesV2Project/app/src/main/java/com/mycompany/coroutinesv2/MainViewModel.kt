package com.mycompany.coroutinesv2

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random

class MainViewModel : ViewModel() {

    // This holds the list of messages (each is a string describing the name + delay)
    val nameListLiveData = MutableLiveData<MutableList<String>>(mutableListOf())

    /**
     * This launches a coroutine that sleeps for a random delay (1..10 seconds),
     * then adds a new string to nameListLiveData.
     */
    fun addName(name: String) {
        viewModelScope.launch(Dispatchers.IO) {
            // random 1..10 seconds
            val randomSeconds = Random.nextInt(1, 11)
            val delayMs = randomSeconds * 1000L

            // Sleep off the main thread
            delay(delayMs)

            // Build the final text
            val result = "The name is $name and the delay was $delayMs milliseconds"

            // Append to the existing list
            val currentList = nameListLiveData.value ?: mutableListOf()
            currentList.add(result)
            nameListLiveData.postValue(currentList)
        }
    }
}
