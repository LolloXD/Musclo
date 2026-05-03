package com.example.musclo

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
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

class BMIActivity : ComponentActivity() //Creando una nuova activity che estende ComponentActivity
{
    override fun onCreate(savedInstanceState: Bundle?)  //Metodo che parte alla creazione dell'activity
    {
        super.onCreate(savedInstanceState) //Inizializza l'activity
        setContentView(R.layout.activity_bmi) //Dice quale grafica usare

        //Elementi ui e variabili
        val etPeso = findViewById<EditText>(R.id.etPeso)
        val etAltezza = findViewById<EditText>(R.id.etAltezza)
        val btnCalcola = findViewById<Button>(R.id.btnCalcola)
        val txtRisultato = findViewById<TextView>(R.id.txtRisultato)

        btnCalcola.setOnClickListener {   //Codice che viene eseguito al click del bottone

            val peso = etPeso.text.toString().toDoubleOrNull()  //prende il testo scritto, lo converte in stringa e prova a convertirlo in numero
            val altezzaCm = etAltezza.text.toString().toDoubleOrNull() //prende il testo scritto, lo converte in stringa e prova a convertirlo in numero

            if (peso != null && altezzaCm != null) //Controlla che i valori siano validi
            {

                val altezzaMetri = altezzaCm / 100 //Converte la cm in metri
                val bmi = peso / (altezzaMetri * altezzaMetri) //Calcola il BMI

                //Decide la categoria in base al BMI

                val categoria = when {
                    bmi < 18.5 -> "Sottopeso"
                    bmi < 25 -> "Normale"
                    bmi < 30 -> "Sovrappeso"
                    else -> "Obesità"
                }

                txtRisultato.text = "BMI: %.2f ($categoria)".format(bmi)  //Mostra il risultato(due cifre dopo la virgola e la categoria)

            } else {
                txtRisultato.text = "Inserisci valori validi" //In caso di valori non validi
            }
        }
    }
}




