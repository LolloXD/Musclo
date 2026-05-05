package com.example.musclo

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
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

    private lateinit var recyclerView: RecyclerView //Ui della lista
    private lateinit var filteredList: ArrayList<ExerciseModel> //Lista filtrata
    private lateinit var adapter: ExerciseAdapter
    private lateinit var exerciseList: ArrayList<ExerciseModel>

    // X login google

    private lateinit var auth : FirebaseAuth
    private lateinit var googleSignInClient: GoogleSignInClient

    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        exerciseList = ArrayList()
        filteredList = ArrayList()

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

        // Per mostrare il nome dell'utente "Ciao ..." registrato con email e password

        val txtWelcome = findViewById<TextView>(R.id.textView)

        val user = FirebaseAuth.getInstance().currentUser
        val name = user?.displayName

        txtWelcome.text = "Ciao, ${name ?: "utente"}!" //Nome è nullo scrive utente



        // X lista esercizi

        recyclerView = findViewById(R.id.recyclerView)

        recyclerView.layoutManager = LinearLayoutManager(this)



            exerciseList.add(
                ExerciseModel(
                    "Chest Press",
                    R.drawable.chest_press,
                    R.drawable.chest_press_animated,
                    "1. Sdraiati sulla panca con i piedi ben piantati a terra\n2. Impugna i manubri all’altezza del petto\n3. Spingi verso l’alto senza bloccare i gomiti\n4. Scendi lentamente controllando il movimento\n5. Mantieni petto aperto e scapole stabili",
                    R.drawable.chestpress_man



                )
            )

        exerciseList.add(
            ExerciseModel(
                "Curl a martello",
                R.drawable.curl_martello,
                R.drawable.hammer_curl_animated,
                "1. In piedi con manubri lungo i fianchi\n2. Palmi rivolti verso il corpo\n3. Solleva i manubri senza oscillare il busto\n4. Contrai i bicipiti in alto\n5. Scendi lentamente con controllo",
                R.drawable.curl_martello_man
            )
        )

        exerciseList.add(
            ExerciseModel(
                "Panca Inclinata",
                R.drawable.distensioni_con_bilanciere_su_panca_inclinata,
                R.drawable.panca_inclinata_animated,
                "1. Sdraiati su panca inclinata 30–45°\n2. Porta il bilanciere sopra il petto alto\n3. Scendi lentamente controllando il movimento\n4. Spingi verso l’alto senza bloccare i gomiti\n5. Mantieni spalle stabili",
                R.drawable.panca_inclinata_man
            )
        )

        exerciseList.add(
            ExerciseModel(
                "Lat pulldown",
                R.drawable.lat_pulldown,
                R.drawable.lat_pulldown_animated,
                "1. Siediti con schiena dritta\n2. Afferra la barra con presa ampia\n3. Tira verso il petto alto\n4. Controlla la risalita del peso\n5. Evita di dondolare il busto",
                R.drawable.lat_pulldown_man
            )
        )

        exerciseList.add(
            ExerciseModel(
                "Leg curl",
                R.drawable.leg_curl,
                R.drawable.leg_curl_animated,
                "1. Posizionati alla macchina e blocca le caviglie\n2. Fletti le gambe verso i glutei\n3. Contrai i femorali in alto\n4. Scendi lentamente\n5. Mantieni controllo del movimento",
                R.drawable.leg_curl_man
            )
        )

        exerciseList.add(
            ExerciseModel(
                "Leg extension",
                R.drawable.leg_extension,
                R.drawable.leg_extension_animated,
                "1. Siediti alla macchina con schiena appoggiata\n2. Estendi le gambe verso l’alto\n3. Contrai i quadricipiti in alto\n4. Scendi lentamente senza rilassare il peso\n5. Movimento controllato",
                R.drawable.leg_extension_man
            )
        )

        exerciseList.add(
            ExerciseModel(
                "Leg press",
                R.drawable.leg_press,
                R.drawable.leg_press_animated,
                "1. Piedi alla larghezza spalle sulla pedana\n2. Scendi fino a 90° con le ginocchia\n3. Spingi senza bloccare le ginocchia\n4. Mantieni schiena sempre appoggiata\n5. Non sollevare i glutei",
                R.drawable.leg_press_man
            )
        )

        exerciseList.add(
            ExerciseModel(
                "Panca piana",
                R.drawable.panca_piana,
                R.drawable.panca_piana_animated,
                "1. Sdraiati con piedi ben piantati\n2. Afferra il bilanciere leggermente più largo delle spalle\n3. Scendi controllando fino al petto\n4. Spingi verso l’alto in modo stabile\n5. Mantieni scapole addotte",
                R.drawable.panca_piana_man
            )
        )

        exerciseList.add(
            ExerciseModel(
                "Squat con bilanciere",
                R.drawable.squat_con_bilanciere,
                R.drawable.squat_bilanciere_animated,
                "1. Bilanciere sulle spalle\n2. Piedi leggermente più larghi delle spalle\n3. Scendi portando i fianchi indietro\n4. Mantieni schiena dritta\n5. Risali spingendo dai talloni",
                R.drawable.squat_bilanciere_man
            )
        )

        exerciseList.add(
            ExerciseModel(
                "Stacco con bilanciere",
                R.drawable.stacco_bilanciere,
                R.drawable.stacco_bilanciere_animated,
                "1. Piedi alla larghezza spalle\n2. Schiena dritta e presa salda\n3. Solleva il bilanciere estendendo anche e ginocchia\n4. Mantieni il peso vicino al corpo\n5. Scendi controllando il movimento",
                R.drawable.stacchi_man
            )
        )

        exerciseList.add(
            ExerciseModel(
                "Croci con manubrio",
                R.drawable.croci_manubrio,
                R.drawable.croci_manubrio_animated,
                "1. Sdraiati sulla panca con piedi ben saldi a terra\n2. Tieni schiena aderente e gomiti leggermente piegati\n3. Apri le braccia lateralmente con movimento controllato\n4. Mantieni i manubri in linea con il petto senza forzare le spalle\n5. Chiudi le braccia lentamente contraendo il petto",
                R.drawable.croci_manubrio_man
            )
        )



        filteredList.addAll(exerciseList)



        // quando clicchi su un esercizio..

             adapter = ExerciseAdapter(filteredList, object : OnItemClickListener {
                override fun onItemClick(position: Int) {

                    val exercise = filteredList[position]

                    // Va ai dettagli
                    val intent = Intent(this@HomeActivity, DetailActivity::class.java)
                    // Passando i dati
                    intent.putExtra("gif", exercise.exerciseGif)
                    intent.putExtra("steps", exercise.steps)
                    intent.putExtra("muscles", exercise.musclesImage)

                    startActivity(intent)
                }
            })


            recyclerView.layoutManager = LinearLayoutManager(this)
            recyclerView.adapter = adapter


            val searchBar = findViewById<EditText>(R.id.searchBar)
        searchBar.addTextChangedListener(object : android.text.TextWatcher {

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            // Ogni volta che scrivo sulla barra di ricerca, richiama la funzione filtro
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                filter(s.toString())
            }

            override fun afterTextChanged(s: android.text.Editable?) {}
        }) // Quando scrivo sulla barra di ricerca si aggiorna sempre



        // Bottone BMI
        val btnBMI = findViewById<Button>(R.id.BMIBtn)

        btnBMI.setOnClickListener {
            // Apre schermata BMI
            val intent = Intent(this@HomeActivity, BMIActivity::class.java)
            startActivity(intent)
        }


        }

    // Ricerca filtro lista esercizi
    private fun filter(text: String) {

        // Riparto da zero ogni volta
        filteredList.clear()

        // Se il campo è vuoto, mostra tutti gli esercizi
        if (text.isEmpty()) {
            filteredList.addAll(exerciseList)
        }
        // Rendi tutto minuscolo per evitare conflitti con le maiuscole
        else {
            val query = text.lowercase()

            for (item in exerciseList) {
                // Se il nome contiene ciò che hai scritto, si aggiunge alla lista
                if (item.exerciseName.lowercase().contains(query)) {
                    filteredList.add(item)
                }
            }
        }

        adapter.notifyDataSetChanged()
    }






    }





