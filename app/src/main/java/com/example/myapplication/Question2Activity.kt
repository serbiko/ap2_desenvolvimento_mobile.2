// Question2Activity.kt  (sem alterações)
package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityQuestion2Binding

class Question2Activity : AppCompatActivity() {
    private lateinit var binding: ActivityQuestion2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuestion2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        val stark = intent.getIntExtra("starkCount", 0)
        val lannister = intent.getIntExtra("lannisterCount", 0)
        val targaryen = intent.getIntExtra("targaryenCount", 0)
        val martell = intent.getIntExtra("martellCount", 0)
        val tyrell = intent.getIntExtra("tyrellCount", 0)

        binding.optionWinter.setOnClickListener {
            goToNext(stark + 1, lannister, targaryen, martell, tyrell, "Stark")
        }
        binding.optionUnbowed.setOnClickListener {
            goToNext(stark, lannister, targaryen, martell + 1, tyrell, "Martell")
        }
        binding.optionGrowing.setOnClickListener {
            goToNext(stark, lannister, targaryen, martell, tyrell + 1, "Tyrell")
        }
        binding.optionFireBlood.setOnClickListener {
            goToNext(stark, lannister, targaryen + 1, martell, tyrell, "Targaryen")
        }
        binding.optionHearRoar.setOnClickListener {
            goToNext(stark, lannister + 1, targaryen, martell, tyrell, "Lannister")
        }
    }

    private fun goToNext(
        stark: Int,
        lannister: Int,
        targaryen: Int,
        martell: Int,
        tyrell: Int,
        secondHouse: String
    ) {
        Intent(this, Question3Activity::class.java).apply {
            putExtra("starkCount", stark)
            putExtra("lannisterCount", lannister)
            putExtra("targaryenCount", targaryen)
            putExtra("martellCount", martell)
            putExtra("tyrellCount", tyrell)
            putExtra("secondHouse", secondHouse)
        }.also {
            startActivity(it)
            finish()
        }
    }
}


