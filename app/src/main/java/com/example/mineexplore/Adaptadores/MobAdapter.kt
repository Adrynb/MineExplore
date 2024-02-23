package com.example.mineexplore.Adaptadores

import MobViewModel
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mineexplore.Mob
import com.example.mineexplore.databinding.FragmentContentBinding
import com.squareup.picasso.Picasso

class MobAdapter(private val viewModel: MobViewModel) :
    RecyclerView.Adapter<MobAdapter.ViewHolder>() {

    var click: ((Int, Mob) -> Unit)? = null

    inner class ViewHolder(private val binding: FragmentContentBinding) : RecyclerView.ViewHolder(binding.root) {
        val nameTextView: TextView = binding.content
        val imageView: ImageView = binding.preImageView
        val seeButton: Button = binding.seeButton

        init {
            seeButton.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val mob = viewModel.mobs.value?.get(position)
                    mob?.let { clickedMob ->
                        click?.invoke(position, clickedMob)
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MobAdapter.ViewHolder {
        return ViewHolder(
            FragmentContentBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        viewModel.mobs.value?.get(position)?.let { mob ->
            holder.nameTextView.text = mob.nombre
            Picasso.get().load(mob.imageURL).into(holder.imageView)
        }
    }

    override fun getItemCount(): Int {
        return viewModel.mobs.value?.size ?: 0
    }
}

