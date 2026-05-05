package com.example.musclo

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.os.postDelayed
import com.example.musclo.ui.theme.MuscloTheme
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.coroutines.delay
import java.util.logging.Handler

class MainActivity : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?) //funzione che viene chiamata quando viene creato l'activity
    {
        super.onCreate(savedInstanceState) // Inzializza l'activity
        setContentView(R.layout.activity_main) //quale grafica usare per la schermata


        supportActionBar?.hide() //nasconde la barra di titolo


        // Splash screen

        android.os.Handler().postDelayed( //Imposta un ritardo di 3 secondi prima di mostrare la schermata di login
            {
                val intent = Intent(this@MainActivity, LoginActivity::class.java) //Passaggio da splash screen a login
                startActivity(intent)
                finish() // Se no si può tornare indietro sulla schermata, che non va bene
            }, (3000)
        )

    }
}

