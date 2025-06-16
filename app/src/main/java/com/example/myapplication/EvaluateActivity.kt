package com.example.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class EvaluateActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_evaluate)

        // carrega o fragment de avaliação dentro do container
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragmentContainer, EvaluateFragment())
            .commit()
    }
}
