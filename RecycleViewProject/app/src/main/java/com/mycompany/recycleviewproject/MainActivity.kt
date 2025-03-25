package com.mycompany.recycleviewproject

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.mycompany.recycleviewproject.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // To create an instance of DataItems
        val data = DataItems()

        // Retrieve the single shuffle of indices from the ViewModel using the length of titles
        val randomIndices = mainViewModel.getShuffledIndices(data.titles.size)

        // Set up the RecyclerView with a LinearLayoutManager and our adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = RecyclerAdapter(data, randomIndices)
    }
}
