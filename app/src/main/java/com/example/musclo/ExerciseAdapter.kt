package com.example.musclo

import android.content.Intent
import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import de.hdodenhof.circleimageview.CircleImageView
import kotlin.jvm.java


// Prende la lista degli esercizi, listener per gestire il click sugli elementi
// ViewHolder rappresenta un singolo elemento della lista (Infatti contiene nome e immagine dell'esercizio

class  ExerciseAdapter(
    private val exerciseList: ArrayList<ExerciseModel>, private val listener: OnItemClickListener) : RecyclerView.Adapter<ExerciseAdapter.ViewHolder>()  {
    class ViewHolder(ItemView : View, listener: OnItemClickListener) : RecyclerView.ViewHolder(ItemView)
    {
        val exerciseName = itemView.findViewById<TextView>(R.id.exercise_name_tv)
        val exerciseImage = itemView.findViewById<CircleImageView>(R.id.exercise_image)

        // Click elemento

        init {
            itemView.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }
    }


    // Creata la riga vuota della lista
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_exercise, parent, false)

        return ViewHolder(view, listener)
    }

    // Riempie la riga con i dati dell'esercizio
    override fun onBindViewHolder(holder: ExerciseAdapter.ViewHolder, position: Int) {
        val exercise = exerciseList[position]

        holder.exerciseName.text = exercise.exerciseName
        holder.exerciseImage.setImageResource(exercise.exerciseImage)

    }

    // Quanti elementi ci sono nella lista
    override fun getItemCount(): Int {
        return exerciseList.size
    }
}