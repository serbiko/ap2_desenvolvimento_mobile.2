package com.example.myapplication

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.ImageView
import android.widget.TextView
import android.widget.Button
import androidx.core.content.FileProvider
import java.io.File
import java.io.FileOutputStream

class ResultActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val tvResult    = findViewById<TextView>(R.id.tvResult)
        val ivCoat      = findViewById<ImageView>(R.id.ivCoat)
        val btnRestart  = findViewById<Button>(R.id.buttonRestart)
        val btnEvaluate = findViewById<Button>(R.id.buttonEvaluate)
        val btnShare    = findViewById<Button>(R.id.buttonShare)
        val btnLearn    = findViewById<Button>(R.id.buttonLearn)

        // 1) lê todos os contadores
        val counts = mapOf(
            "Stark"      to intent.getIntExtra("starkCount", 0),
            "Lannister"  to intent.getIntExtra("lannisterCount", 0),
            "Targaryen"  to intent.getIntExtra("targaryenCount", 0),
            "Martell"    to intent.getIntExtra("martellCount", 0),
            "Tyrell"     to intent.getIntExtra("tyrellCount", 0)
        )

        // 2) escolhe quem tem maior valor
        val house = counts.maxByOrNull { it.value }?.key ?: "Stark"
        tvResult.text = "Você pertence à Casa $house"

        // 3) mostra o brasão correto
        val coatRes = when (house) {
            "Stark"      -> R.drawable.stark
            "Lannister"  -> R.drawable.lannister
            "Martell"    -> R.drawable.martell
            "Tyrell"     -> R.drawable.tyrell
            "Targaryen"  -> R.drawable.targaryen
            else         -> R.drawable.stark
        }
        ivCoat.setImageResource(coatRes)

        // 4) “Recomeçar”
        btnRestart.setOnClickListener {
            Intent(this, MainActivity::class.java).apply {
                addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(this)
            }
            finish()
        }

        // 5) “Avaliar”
        btnEvaluate.setOnClickListener {
            startActivity(Intent(this, EvaluateActivity::class.java))
        }

        // 6) “Saiba Mais”
        val wikiUrl = "https://wiki.geloefogo.com/index.php/Casa_$house"
        btnLearn.setOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(wikiUrl)))
        }

        // 7) “Compartilhar”
        btnShare.setOnClickListener {
            val shareBmp = createShareBitmap(tvResult.text.toString(), coatRes)
            val cachePath = File(cacheDir, "images").apply { mkdirs() }
            val file = File(cachePath, "share.png")
            FileOutputStream(file).use { out ->
                shareBmp.compress(Bitmap.CompressFormat.PNG, 100, out)
            }
            val uri = FileProvider.getUriForFile(this, "$packageName.fileprovider", file)
            startActivity(Intent.createChooser(
                Intent(Intent.ACTION_SEND).apply {
                    type = "image/png"
                    putExtra(Intent.EXTRA_STREAM, uri)
                    addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                },
                "Compartilhar via"
            ))
        }
    }

    private fun createShareBitmap(text: String, coatResId: Int): Bitmap {
        val coat = BitmapFactory.decodeResource(resources, coatResId)
        val width = coat.width + 100
        val height = coat.height + 200
        val bmp = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bmp)

        // fundo cinza escuro
        canvas.drawColor(Color.parseColor("#333333"))

        // escreve o texto no topo
        val paint = Paint().apply {
            color = Color.WHITE
            textSize = 48f
            textAlign = Paint.Align.CENTER
        }
        canvas.drawText(text, width / 2f, 60f, paint)

        // desenha o brasão abaixo do texto
        canvas.drawBitmap(coat, (width - coat.width) / 2f, 80f, null)

        return bmp
    }
}
