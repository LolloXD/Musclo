package com.example.musclo

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.ComponentActivity
import com.bumptech.glide.Glide

class DetailActivity : ComponentActivity() { // Parte all'interno degli esercizi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

            val imageView = findViewById<ImageView>(R.id.imgEsercizio)

        val intent = intent //Serve per passare dati tra activity


        val resId = intent.getIntExtra("gif", 0) //gif dell'esercizio

        val steps = intent.getStringExtra("steps") //Istruzioni dell'esercizio


        val txtSteps = findViewById<TextView>(R.id.txtSteps) //Riporta le istruzioni dell'esercizio

        txtSteps.text = steps //Salva le istruzioni dell'esercizio


        val musclesRes = intent.getIntExtra("muscles", 0)

        val imgMuscoli = findViewById<ImageView>(R.id.imgMuscoli)
        imgMuscoli.setImageResource(musclesRes) //mette immagine del muscolo corretto attraverso la grafica

        Glide.with(this) //libreria per caricare immagini/gif
            .asGif()
            .load(resId)
            .into(imageView)

    }
}