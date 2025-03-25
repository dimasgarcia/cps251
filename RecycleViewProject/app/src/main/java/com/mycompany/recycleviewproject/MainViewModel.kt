package com.mycompany.recycleviewproject

import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    private var shuffledIndices: List<Int>? = null

    fun getShuffledIndices(dataSize: Int): List<Int> {
        if (shuffledIndices == null) {
            shuffledIndices = (0 until dataSize).toList().shuffled()
        }
        return shuffledIndices!!
    }
}
