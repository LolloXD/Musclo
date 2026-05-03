package com.example.musclo

import android.R.attr.name
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
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

class SignInActivity : ComponentActivity() {

    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signin)

        // Firebase
        auth = FirebaseAuth.getInstance()

        // Views (Riferimenti campi di layout)
        val nameInput = findViewById<EditText>(R.id.name_input)
        val emailInput = findViewById<EditText>(R.id.email_input)
        val passwordInput = findViewById<EditText>(R.id.password_input)
        val registerBtn = findViewById<Button>(R.id.register_btn)

        // Quando premi sulk bottone, leggi i dati inseriti
        registerBtn.setOnClickListener {



            val name = nameInput.text.toString()
            val email = emailInput.text.toString()
            val password = passwordInput.text.toString()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Compila tutti i campi", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                Toast.makeText(this, "Email non valida", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (password.length < 6) {
                Toast.makeText(this, "Password minimo 6 caratteri", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Creazione utente firebase
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {

                        // Se ha successo, prendo il nome utente e imposto il nome (Per poi salvarlo sulla console di firebase)

                        val user = FirebaseAuth.getInstance().currentUser

                        val profileUpdates = com.google.firebase.auth.UserProfileChangeRequest.Builder()
                            .setDisplayName(name)
                            .build()

                        user?.updateProfile(profileUpdates)

                        Toast.makeText(this, "Registrato!", Toast.LENGTH_SHORT).show()
                    }

                    else {

                        Toast.makeText(
                            this,
                            "ERRORE: ${task.exception?.message}",
                            Toast.LENGTH_LONG
                        ).show()

                    }

                }
                }

            // Per mostrare il nome registrandosi con email e password



        }
    }





