package com.example.mineexplore.DetailsFragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.mineexplore.Fragments.BlockFragment
import com.example.mineexplore.Fragments.ItemFragment
import com.example.mineexplore.R
import com.example.mineexplore.ViewModels.ItemViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.squareup.picasso.Picasso

class DetailItem : Fragment(){
    companion object {
        fun newInstance() = DetailItem()
    }

    private val viewModel : ItemViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_detail_item, container, false)

        val nombreDetailItem = view.findViewById<TextView>(R.id.nombreItemDetail)
        val imagenDetailItem = view.findViewById<ImageView>(R.id.imagenItemDetail)
        val descripcionDetailItem = view.findViewById<TextView>(R.id.descriptionItemDetail)

        viewModel.selectedItem?.let { block ->
            nombreDetailItem.text = block.nombre
            descripcionDetailItem.text = block.descripcion
            Picasso.get().load(block.imageURL).into(imagenDetailItem)
        }

        view.findViewById<FloatingActionButton>(R.id.floatingDetailItemButton).setOnClickListener{
            parentFragmentManager.beginTransaction().apply {
                replace(R.id.fragment_container, ItemFragment())
                addToBackStack("replacement")
                commit()
            }
        }

        val editButton : Button = view.findViewById(R.id.editarListaItem)
        editButton.setOnClickListener{

        }

        return view
    }
}