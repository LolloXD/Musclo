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

class BMIActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bmi)

        val etPeso = findViewById<EditText>(R.id.etPeso)
        val etAltezza = findViewById<EditText>(R.id.etAltezza)
        val btnCalcola = findViewById<Button>(R.id.btnCalcola)
        val txtRisultato = findViewById<TextView>(R.id.txtRisultato)

        btnCalcola.setOnClickListener {

            val peso = etPeso.text.toString().toDoubleOrNull()
            val altezzaCm = etAltezza.text.toString().toDoubleOrNull()

            if (peso != null && altezzaCm != null) {

                val altezzaMetri = altezzaCm / 100
                val bmi = peso / (altezzaMetri * altezzaMetri)

                val categoria = when {
                    bmi < 18.5 -> "Sottopeso"
                    bmi < 25 -> "Normale"
                    bmi < 30 -> "Sovrappeso"
                    else -> "Obesità"
                }

                txtRisultato.text = "BMI: %.2f ($categoria)".format(bmi)

            } else {
                txtRisultato.text = "Inserisci valori validi"
            }
        }
    }
}




