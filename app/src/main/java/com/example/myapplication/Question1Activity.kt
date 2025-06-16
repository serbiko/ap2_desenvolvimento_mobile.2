// Question1Activity.kt
package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class Question1Activity : AppCompatActivity() {

    private var stark = 0
    private var lannister = 0
    private var martell = 0
    private var tyrell = 0
    private var targaryen = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question1)

        // agora lendo com as chaves “...Count”
        stark = intent.getIntExtra("starkCount", 0)
        lannister = intent.getIntExtra("lannisterCount", 0)
        martell = intent.getIntExtra("martellCount", 0)
        tyrell = intent.getIntExtra("tyrellCount", 0)
        targaryen = intent.getIntExtra("targaryenCount", 0)

        findViewById<Button>(R.id.btnQ1Opt1).setOnClickListener {
            // cinza e branco → Stark
            goToNext(stark + 1, lannister, martell, tyrell, targaryen)
        }
        findViewById<Button>(R.id.btnQ1Opt2).setOnClickListener {
            // vermelho e dourado → Lannister
            goToNext(stark, lannister + 1, martell, tyrell, targaryen)
        }
        findViewById<Button>(R.id.btnQ1Opt3).setOnClickListener {
            // vermelho e preto → Targaryen
            goToNext(stark, lannister, martell, tyrell, targaryen + 1)
        }
        findViewById<Button>(R.id.btnQ1Opt4).setOnClickListener {
            // verde e dourado → Tyrell
            goToNext(stark, lannister, martell, tyrell + 1, targaryen)
        }
        findViewById<Button>(R.id.btnQ1Opt5).setOnClickListener {
            // amarelo e laranja → Martell
            goToNext(stark, lannister, martell + 1, tyrell, targaryen)
        }
    }

    private fun goToNext(s: Int, l: Int, m: Int, t: Int, ta: Int) {
        Intent(this, Question2Activity::class.java).also {
            it.putExtra("starkCount", s)
            it.putExtra("lannisterCount", l)
            it.putExtra("martellCount", m)
            it.putExtra("tyrellCount", t)
            it.putExtra("targaryenCount", ta)
            startActivity(it)
        }
        finish()
    }
}
