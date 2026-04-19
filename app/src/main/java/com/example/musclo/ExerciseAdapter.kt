package com.example.musclo

import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import de.hdodenhof.circleimageview.CircleImageView

class ExerciseAdapter(private val exerciseList : ArrayList<ExerciseModel>) : RecyclerView.Adapter<ExerciseAdapter.ViewHolder>() {
    class ViewHolder(ItemView : View) : RecyclerView.ViewHolder(ItemView)
    {
        val exerciseName = itemView.findViewById<TextView>(R.id.exercise_name_tv)
        val exerciseImage = itemView.findViewById<CircleImageView>(R.id.exercise_image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerciseAdapter.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_exercise,parent, false))
    }

    override fun onBindViewHolder(holder: ExerciseAdapter.ViewHolder, position: Int) {
        val exercise = exerciseList[position]

        holder.exerciseName.text = exercise.exerciseName
        holder.exerciseImage.setImageResource(exercise.exerciseImage)
    }

    override fun getItemCount(): Int {
        return exerciseList.size
    }
}