package com.mycompany.recycleintent

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mycompany.recycleintent.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Get the index that was clicked
        val clickedIndex = intent.getIntExtra("INDEX_CLICKED", -1)

        // Retrieve our data arrays
        val data = DataItems()

        if (clickedIndex >= 0 && clickedIndex < data.titles.size) {
            binding.secondTitle.text = data.titles[clickedIndex]
            binding.secondDetail.text = data.details[clickedIndex]
            binding.secondImage.setImageResource(data.images[clickedIndex])
        }
    }
}
