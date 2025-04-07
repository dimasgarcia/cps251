package com.mycompany.coroutinesv2

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.mycompany.coroutinesv2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    // Lazy-initialize the ViewModel so it persists across rotation
    private val mainViewModel: MainViewModel by viewModels()

    // This sets this adapter once or recreate it each time the list changes
    private var adapter: RecyclerAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 1) Inflate layout with ViewBinding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 2) Initialize RecyclerView
        binding.rvNames.layoutManager = LinearLayoutManager(this)

        // 3) Observe the list in the ViewModel
        mainViewModel.nameListLiveData.observe(this) { newList ->
            adapter = RecyclerAdapter(newList)
            binding.rvNames.adapter = adapter
        }

        // 4) Add Button click
        binding.btnAddName.setOnClickListener {
            val nameEntered = binding.etName.text.toString().trim()
            if (nameEntered.isNotEmpty()) {
                mainViewModel.addName(nameEntered)
                binding.etName.setText("")
            }
        }
    }
}
