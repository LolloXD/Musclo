package com.example.musclo

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.ComponentActivity
import com.bumptech.glide.Glide

class DetailActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val imageView = findViewById<ImageView>(R.id.imgEsercizio)

        val resId = intent.getIntExtra("gif", 0)
        val steps = intent.getStringExtra("steps")

        val txtSteps = findViewById<TextView>(R.id.txtSteps)
        txtSteps.text = steps

        val musclesRes = intent.getIntExtra("muscles", 0)

        val imgMuscoli = findViewById<ImageView>(R.id.imgMuscoli)
        imgMuscoli.setImageResource(musclesRes)

        Glide.with(this)
            .asGif()
            .load(resId)
            .into(imageView)

    }
}