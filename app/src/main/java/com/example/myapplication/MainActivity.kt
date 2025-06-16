// MainActivity.kt
package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.buttonStart).setOnClickListener {
            Intent(this, Question1Activity::class.java).also {
                it.putExtra("starkCount", 0)
                it.putExtra("lannisterCount", 0)
                it.putExtra("martellCount", 0)
                it.putExtra("tyrellCount", 0)
                it.putExtra("targaryenCount", 0)
                startActivity(it)
            }
            finish()
        }
    }
}


