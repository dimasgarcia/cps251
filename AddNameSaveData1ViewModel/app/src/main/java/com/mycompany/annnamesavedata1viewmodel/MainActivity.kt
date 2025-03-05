package com.mycompany.annnamesavedata1viewmodel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.mycompany.annnamesavedata1viewmodel.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]

        // Observes the names LiveData from the ViewModel
        mainViewModel.names.observe(this) { names ->
            if (names.isEmpty()) {
                binding.displayNames.text = getString(R.string.no_names_to_display)
            } else {
                binding.displayNames.text = names.joinToString(separator = "\n")
            }
        }

        binding.buttonAddName.setOnClickListener {
            val enteredName = binding.editTextName.text.toString().trim()
            if (enteredName.isEmpty()) {
                binding.displayNames.text = getString(R.string.no_name_entered)
            } else {
                mainViewModel.addName(enteredName)
                binding.editTextName.text.clear()
            }
        }
    }
}
