// Question3Activity.kt  (sem alterações)
package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityQuestion3Binding

class Question3Activity : AppCompatActivity() {
    private lateinit var binding: ActivityQuestion3Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuestion3Binding.inflate(layoutInflater)
        setContentView(binding.root)

        val stark = intent.getIntExtra("starkCount", 0)
        val lannister = intent.getIntExtra("lannisterCount", 0)
        val targaryen = intent.getIntExtra("targaryenCount", 0)
        val martell = intent.getIntExtra("martellCount", 0)
        val tyrell = intent.getIntExtra("tyrellCount", 0)
        val secondHouse = intent.getStringExtra("secondHouse") ?: ""

        binding.optionLegacy.setOnClickListener {
            goToNext(stark, lannister + 1, targaryen, martell, tyrell, secondHouse)
        }
        binding.optionHonor.setOnClickListener {
            goToNext(stark + 1, lannister, targaryen, martell, tyrell, secondHouse)
        }
        binding.optionDetermination.setOnClickListener {
            goToNext(stark, lannister, targaryen, martell + 1, tyrell, secondHouse)
        }
        binding.optionPower.setOnClickListener {
            goToNext(stark + 1, lannister, targaryen, martell, tyrell, secondHouse)
        }
        binding.optionAmbition.setOnClickListener {
            goToNext(stark, lannister, targaryen, martell, tyrell + 1, secondHouse)
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
        Intent(this, Question4Activity::class.java).apply {
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

