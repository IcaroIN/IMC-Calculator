package com.example.imccalculator

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    @SuppressLint("StringFormatMatches")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val name = findViewById<EditText>(R.id.name)
        val age = findViewById<EditText>(R.id.age)
        val weight = findViewById<EditText>(R.id.weight)
        val height = findViewById<EditText>(R.id.height)
        val calculate = findViewById<Button>(R.id.calculateButton)
        val result = findViewById<TextView>(R.id.result)
        val image = findViewById<ImageView>(R.id.image)

        calculate.setOnClickListener {
            val nameValue = name.text.toString()
            val ageValue = age.text.toString().toInt()
            val weightValue = weight.text.toString().toDouble()
            val heightValue = height.text.toString().toDouble() / 100 // Converter altura para metros
            val bmi = calculateBMI(weightValue, heightValue)

            val bmiCategoryResId = when {
                bmi < 18.5 -> R.string.underweight
                bmi < 24.9 -> R.string.normal_weight
                bmi < 29.9 -> R.string.overweight
                bmi < 34.9 -> R.string.obesity_class_I
                bmi < 39.9 -> R.string.obesity_class_II
                else -> R.string.obesity_class_III
            }

            result.text = getString(R.string.bmi_category, nameValue, ageValue, getString(bmiCategoryResId))

            when (bmiCategoryResId) {
                R.string.underweight -> image.setImageResource(R.drawable.underweight)
                R.string.normal_weight -> image.setImageResource(R.drawable.normal_weight)
                R.string.overweight -> image.setImageResource(R.drawable.overweight)
                R.string.obesity_class_I -> image.setImageResource(R.drawable.obesityclass_1)
                R.string.obesity_class_II -> image.setImageResource(R.drawable.obesityclass_2)
                R.string.obesity_class_III -> image.setImageResource(R.drawable.obesityclass_3)
            }
        }
    }

    private fun calculateBMI(weight: Double, height: Double): Double {
        return weight / (height * height)
    }
}
