package com.example.mineexplore.Adaptadores

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mineexplore.Mob
import com.example.mineexplore.R
import com.squareup.picasso.Picasso

class MobAdapter(private val values: List<Mob>) :
    RecyclerView.Adapter<MobAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView = itemView.findViewById(R.id.editTextName)
        val descriptionTextView: TextView = itemView.findViewById(R.id.editTextDescription)
        val imageView: ImageView = itemView.findViewById(R.id.imagePreview)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_mob, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val mob = values[position]
        holder.nameTextView.text = mob.nombre
        holder.descriptionTextView.text = mob.descripcion
        Picasso.get().load(mob.imageURL).into(holder.imageView)
    }

    override fun getItemCount(): Int {
        return values.size
    }
}
