package com.example.musclo

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.musclo.ui.theme.MuscloTheme
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth

class HomeActivity : ComponentActivity() {

    // X lista esercizi (RecycleView)

    private lateinit var recyclerView: RecyclerView

    // X login google

    private lateinit var auth : FirebaseAuth
    private lateinit var googleSignInClient: GoogleSignInClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        auth = FirebaseAuth.getInstance()

        val email = intent.getStringExtra("Email")
        val displayName = intent.getStringExtra("Name")
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        val nome = intent.getStringExtra("USER_NAME")

        googleSignInClient = GoogleSignIn.getClient(this, gso)

        findViewById<TextView>(R.id.textView).text = "Ciao, $nome!"

        findViewById<Button>(R.id.signOutBtn).setOnClickListener {
            googleSignInClient.signOut().addOnCompleteListener {

                googleSignInClient.revokeAccess().addOnCompleteListener {

                    auth.signOut()

                    val intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }
        }

        // X lista esercizi

        recyclerView = findViewById(R.id.recyclerView)

        recyclerView.layoutManager = LinearLayoutManager(this)

        val exerciseList = ArrayList<ExerciseModel>()

        exerciseList.add(ExerciseModel("Chest Press", R.drawable.chest_press))
        // Continuare ad aggiungere..

        val adapter = ExerciseAdapter(exerciseList)

        recyclerView.adapter = adapter



    }


}