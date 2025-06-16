package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityQuestion5Binding

class Question5Activity : AppCompatActivity() {
    private lateinit var binding: ActivityQuestion5Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuestion5Binding.inflate(layoutInflater)
        setContentView(binding.root)

        val stark = intent.getIntExtra("starkCount", 0)
        val lannister = intent.getIntExtra("lannisterCount", 0)
        val targaryen = intent.getIntExtra("targaryenCount", 0)
        val martell = intent.getIntExtra("martellCount", 0)
        val tyrell = intent.getIntExtra("tyrellCount", 0)
        val secondHouse = intent.getStringExtra("secondHouse") ?: ""

        binding.optionCasterly.setOnClickListener {
            finishWith(stark, lannister + 1, targaryen, martell, tyrell, secondHouse)
        }
        binding.optionDragonstone.setOnClickListener {
            finishWith(stark, lannister, targaryen + 1, martell, tyrell, secondHouse)
        }
        binding.optionWinterfell.setOnClickListener {
            finishWith(stark + 1, lannister, targaryen, martell, tyrell, secondHouse)
        }
        binding.optionHighgarden.setOnClickListener {
            finishWith(stark, lannister, targaryen, martell, tyrell + 1, secondHouse)
        }
        binding.optionSunspear.setOnClickListener {
            finishWith(stark, lannister, targaryen, martell + 1, tyrell, secondHouse)
        }
    }

    private fun finishWith(
        stark: Int,
        lannister: Int,
        targaryen: Int,
        martell: Int,
        tyrell: Int,
        secondHouse: String
    ) {
        Intent(this, ResultActivity::class.java).apply {
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


