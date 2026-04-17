package com.example.musclo

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.musclo.ui.theme.MuscloTheme
import com.google.firebase.auth.FirebaseAuth

class HomeActivity : ComponentActivity() {

    // X login google

    private lateinit var auth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        auth = FirebaseAuth.getInstance()

        val email = intent.getStringExtra("Email")
        val displayName = intent.getStringExtra("Name")

        findViewById<TextView>(R.id.textView).text = email + "\n" + displayName

        findViewById<Button>(R.id.signOutBtn).setOnClickListener {
            auth.signOut()
            startActivity(Intent(this , MainActivity::class.java))
        }



    }
}