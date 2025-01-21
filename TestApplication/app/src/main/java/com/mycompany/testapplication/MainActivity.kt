package com.mycompany.testapplication

import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import java.text.DecimalFormat

class MainActivity() : AppCompatActivity(), Parcelable {
    constructor(parcel: Parcel) : this() {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MainActivity> {
        override fun createFromParcel(parcel: Parcel): MainActivity {
            return MainActivity(parcel)
        }

        override fun newArray(size: Int): Array<MainActivity?> {
            return arrayOfNulls(size)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize UI components using findViewById
        val etAmount = findViewById<EditText>(R.id.etAmount)
        val btnConvert = findViewById<Button>(R.id.btnConvert)
        val tvResult = findViewById<TextView>(R.id.tvResult)

        // Set up the button click listener
        btnConvert.setOnClickListener {
            // Get the amount entered in the EditText, convert it to a Double, and handle possible null values
            val usdAmount = etAmount.text.toString().toDoubleOrNull()
            if (usdAmount != null) {
                // Convert the USD amount to Euros at a conversion rate (example rate is 0.93)
                val euroAmount = usdAmount * 0.93 // Example conversion rate
                val formatter = DecimalFormat("#,###.00") // Format to two decimal places
                tvResult.text =
                    "€${formatter.format(euroAmount)}" // Display the formatted result in the TextView
            } else {
                // Inform the user if the input was not a valid number
                tvResult.text = "Please enter a valid amount"
            }
        }
    }}