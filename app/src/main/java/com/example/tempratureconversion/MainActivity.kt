package com.example.tempratureconversion

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText

class MainActivity : AppCompatActivity() {

    private val celsiusEditText by lazy { findViewById<EditText>(R.id.cel) }
    private val fahrenheitEditText by lazy { findViewById<EditText>(R.id.far) }
    private var isCelsiusBeingUpdated = false
    private var isFahrenheitBeingUpdated = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        celsiusEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (!isCelsiusBeingUpdated) {
                    isFahrenheitBeingUpdated = true
                    val fahrenheitValue = toFahrenheit(s.toString())
                    fahrenheitEditText.setText(fahrenheitValue)
                    isFahrenheitBeingUpdated = false
                }
            }
        })

        fahrenheitEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (!isFahrenheitBeingUpdated) {
                    isCelsiusBeingUpdated = true
                    val celsiusValue = toCelsius(s.toString())
                    celsiusEditText.setText(celsiusValue)
                    isCelsiusBeingUpdated = false
                }
            }
        })
    }

    private fun toFahrenheit(celsius: String): String {
        if (celsius.isNotEmpty()) {
            val celsiusValue = celsius.toDouble()
            val fahrenheit = (celsiusValue * 9 / 5) + 32
            return fahrenheit.toString()
        }
        return ""
    }

    private fun toCelsius(fahrenheit: String): String {
        if (fahrenheit.isNotEmpty()) {
            val fahrenheitValue = fahrenheit.toDouble()
            val celsius = (fahrenheitValue - 32) * 5 / 9
            return celsius.toString()
        }
        return ""
    }
}
