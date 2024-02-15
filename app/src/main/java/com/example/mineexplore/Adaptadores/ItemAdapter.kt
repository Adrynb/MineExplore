package com.example.mineexplore.Adaptadores

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mineexplore.Block
import com.example.mineexplore.Items
import com.example.mineexplore.R
import com.example.mineexplore.ViewModels.ItemViewModel
import com.example.mineexplore.databinding.FragmentContentBinding
import com.squareup.picasso.Picasso

class ItemAdapter(private val viewModel: ItemViewModel) :
    RecyclerView.Adapter<ItemAdapter.ViewHolder>() {

    var click: ((Int, Items) -> Unit)? = null

    inner class ViewHolder(private val binding: FragmentContentBinding) : RecyclerView.ViewHolder(binding.root) {
        val idView : TextView = binding.itemNumber
        val nameTextView: TextView = binding.content
        val imageView: ImageView = binding.preImageView
        val seeButton: Button = binding.seeButton

        init {
            seeButton.setOnClickListener {
                click?.invoke(adapterPosition, viewModel.items[adapterPosition])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            FragmentContentBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false

            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = viewModel.items[position]
        holder.nameTextView.text = item.nombre
        Picasso.get().load(item.imageURL).into(holder.imageView)
    }

    override fun getItemCount(): Int = viewModel.items.size
}
