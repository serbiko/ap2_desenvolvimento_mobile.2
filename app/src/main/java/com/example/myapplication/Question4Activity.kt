package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityQuestion4Binding

class Question4Activity : AppCompatActivity() {
    private lateinit var binding: ActivityQuestion4Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuestion4Binding.inflate(layoutInflater)
        setContentView(binding.root)

        val stark = intent.getIntExtra("starkCount", 0)
        val lannister = intent.getIntExtra("lannisterCount", 0)
        val targaryen = intent.getIntExtra("targaryenCount", 0)
        val martell = intent.getIntExtra("martellCount", 0)
        val tyrell = intent.getIntExtra("tyrellCount", 0)
        val secondHouse = intent.getStringExtra("secondHouse") ?: ""

        binding.optionFlower.setOnClickListener {
            goToNext(stark, lannister, targaryen, martell, tyrell + 1, secondHouse)
        }
        binding.optionDragon.setOnClickListener {
            goToNext(stark, lannister, targaryen + 1, martell, tyrell, secondHouse)
        }
        binding.optionLion.setOnClickListener {
            goToNext(stark, lannister + 1, targaryen, martell, tyrell, secondHouse)
        }
        binding.optionSun.setOnClickListener {
            goToNext(stark, lannister, targaryen, martell + 1, tyrell, secondHouse)
        }
        binding.optionWolf.setOnClickListener {
            goToNext(stark + 1, lannister, targaryen, martell, tyrell, secondHouse)
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
        Intent(this, Question5Activity::class.java).apply {
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

