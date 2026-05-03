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
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.musclo.ui.theme.MuscloTheme
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider

class LoginActivity : ComponentActivity() //Creiamo un attività che estende ComponentActivity
{

    private lateinit var auth : FirebaseAuth  //Serve per gestire l'autenticazione Firebase
    private lateinit var googleSignInClient : GoogleSignInClient // Serve per gestire l'autenticazione con Google


    override fun onCreate(savedInstanceState: Bundle?)  //funzione che viene chiamata quando viene creato l'activity
    {
        super.onCreate(savedInstanceState) // Inzializza l'activity
        setContentView(R.layout.activity_login) //Dice quale grafica usare

        auth = FirebaseAuth.getInstance() //Istanza fire per login

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN) //Creazione dell'oggetto GoogleSignInOptions per gestire l'autenticazione con Google
            .requestIdToken(getString(R.string.default_web_client_id)) //Collega google al firebase, il token identifica utente
            .requestEmail() //Ottenere email dell'utente
            .build() //Costruisce oggetto

        googleSignInClient = GoogleSignIn.getClient(this , gso) //Oggetto per aprire login di google

        findViewById<Button>(R.id.gSignInBtn).setOnClickListener {
            signInGoogle()

        }//Apre un altra schermata serve per registrarsi

        findViewById<Button>(R.id.register_btn).setOnClickListener {
            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
        }

        // Fino a qui google


        //Campi email/password

        val emailInput = findViewById<EditText>(R.id.email_input)
        val passwordInput = findViewById<EditText>(R.id.password_input)
        val loginBtn = findViewById<Button>(R.id.login_btn)

        loginBtn.setOnClickListener {

            val email = emailInput.text.toString().trim()
            val password = passwordInput.text.toString().trim()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Compila tutti i campi", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->

                    if (task.isSuccessful) {
                        Toast.makeText(this, "Login riuscito!", Toast.LENGTH_SHORT).show()

                        val intent = Intent(this, HomeActivity::class.java)
                        startActivity(intent)
                        finish()

                    } else {
                        Toast.makeText(this, task.exception?.message, Toast.LENGTH_LONG).show()
                    }
                }
        }

        // Fino a qui email/password

        }
    private fun signInGoogle()
    {
        val signInIntent = googleSignInClient.signInIntent
        launcher.launch(signInIntent)
    }

    private val launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult())
    {
            result ->
        if (result.resultCode == Activity.RESULT_OK)
        {
            val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
            handleResults(task)
        }
    }

    private fun handleResults(task: Task<GoogleSignInAccount>) {
        if (task.isSuccessful)
        {
            val account : GoogleSignInAccount? = task.result
            if (account != null)
            {
                updateUI(account)
            }
        }
        else
        {
            Toast.makeText(this, task.exception.toString(), Toast.LENGTH_SHORT).show()
        }
    }


    private fun updateUI(account: GoogleSignInAccount) {
        val credential = GoogleAuthProvider.getCredential(account.idToken , null)
        auth.signInWithCredential(credential).addOnCompleteListener{
            if (it.isSuccessful)
            {
                val intent : Intent = Intent(this , HomeActivity::class.java)
                intent.putExtra( "Loggato come" , account.email)
                intent.putExtra( "USER_NAME" , account.displayName)
                startActivity(intent)
                finish()
            }
            else{
                Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()
            }
        }
    }



}




