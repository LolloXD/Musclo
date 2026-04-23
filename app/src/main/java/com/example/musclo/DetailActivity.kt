package com.example.musclo

import android.os.Bundle
import android.widget.ImageView
import androidx.activity.ComponentActivity
import com.bumptech.glide.Glide

class DetailActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val imageView = findViewById<ImageView>(R.id.imgEsercizio)

        val resId = intent.getIntExtra("gif", 0)

        Glide.with(this)
            .asGif()
            .load(resId)
            .into(imageView)
    }
}