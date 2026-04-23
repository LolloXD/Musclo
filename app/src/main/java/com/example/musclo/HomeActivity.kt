package com.example.musclo

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth

class HomeActivity : AppCompatActivity() {

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



        // Continuare ad aggiungere..



            val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)



            exerciseList.add(
                ExerciseModel(
                    "Chest Press",
                    R.drawable.chest_press,
                    R.drawable.chest_press_animated



                )
            )

        exerciseList.add(
            ExerciseModel(
                "Curl a martello",
                R.drawable.curl_martello,
                R.drawable.hammer_curl_animated
            )
        )

        exerciseList.add(
            ExerciseModel(
                "Panca Inclinata",
                R.drawable.distensioni_con_bilanciere_su_panca_inclinata,
                R.drawable.panca_inclinata_animated
            )
        )

        exerciseList.add(
            ExerciseModel(
                "Lat pulldown",
                R.drawable.lat_pulldown,
                R.drawable.lat_pulldown_animated
            )
        )

        exerciseList.add(
            ExerciseModel(
                "Leg curl",
                R.drawable.leg_curl,
                R.drawable.leg_curl_animated
            )
        )

        exerciseList.add(
            ExerciseModel(
                "Leg extension",
                R.drawable.leg_extension,
                R.drawable.leg_extension_animated
            )
        )

        exerciseList.add(
            ExerciseModel(
                "Leg press",
                R.drawable.leg_press,
                R.drawable.leg_press_animated
            )
        )

        exerciseList.add(
            ExerciseModel(
                "Panca piana",
                R.drawable.panca_piana,
                R.drawable.panca_piana_animated
            )
        )

        exerciseList.add(
            ExerciseModel(
                "Squat con bilanciere",
                R.drawable.squat_con_bilanciere,
                R.drawable.squat_bilanciere_animated
            )
        )

        exerciseList.add(
            ExerciseModel(
                "Stacco con bilanciere",
                R.drawable.stacco_bilanciere,
                R.drawable.stacco_bilanciere_animated
            )
        )



            val adapter = ExerciseAdapter(exerciseList, object : OnItemClickListener {
                override fun onItemClick(position: Int) {

                    val exercise = exerciseList[position]

                    val intent = Intent(this@HomeActivity, DetailActivity::class.java)
                    intent.putExtra("gif", exercise.exerciseGif)

                    startActivity(intent)
                }
            })

            recyclerView.layoutManager = LinearLayoutManager(this)
            recyclerView.adapter = adapter
        }



    }


