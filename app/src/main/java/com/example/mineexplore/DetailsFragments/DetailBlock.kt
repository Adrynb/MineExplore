package com.example.mineexplore.DetailsFragments

import BlockViewModel
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
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
import com.example.mineexplore.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.squareup.picasso.Picasso

class DetailBlock : Fragment() {

    private lateinit var viewModel: BlockViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_detail_block, container, false)

        val nombreDetailTextView = view.findViewById<TextView>(R.id.nombreBlockDetail)
        val descripcionDetailTextView = view.findViewById<TextView>(R.id.descripcionBlockDetail)
        val imageURLDetailImageView = view.findViewById<ImageView>(R.id.imagenDetailBlock)

        viewModel = ViewModelProvider(requireActivity()).get(BlockViewModel::class.java)

        viewModel.selectedBlockLiveData.observe(viewLifecycleOwner, Observer { block ->
            block?.let {
                nombreDetailTextView.text = it.nombre
                descripcionDetailTextView.text = it.descripcion
                Picasso.get().load(it.imageURL).into(imageURLDetailImageView)
            }
        })

        view.findViewById<FloatingActionButton>(R.id.floatingDetailBlockButton).setOnClickListener {
            parentFragmentManager.beginTransaction().apply {
                replace(R.id.fragment_container, BlockFragment())
                addToBackStack("replacement")
                commit()
            }
        }

        val editButton: Button = view.findViewById<Button?>(R.id.editarLista)
        editButton.setOnClickListener {

        }

        return view
    }

    companion object {
        fun newInstance() {
            apply {

            }
        }
    }
}
