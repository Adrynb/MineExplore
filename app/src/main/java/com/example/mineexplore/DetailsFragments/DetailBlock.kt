package com.example.mineexplore.DetailsFragments
import BlockViewModel
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.mineexplore.R
import com.squareup.picasso.Picasso

class DetailBlock : Fragment() {

    companion object {
        fun newInstance() = DetailBlock()
    }

    private val viewModel: BlockViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_detail_block, container, false)

        val nombreDetailTextView = view.findViewById<TextView>(R.id.nombreDetail)
        val descripcionDetailTextView = view.findViewById<TextView>(R.id.descripcionDetail)
        val imageURLDetailImageView = view.findViewById<ImageView>(R.id.imageURLDetail)

        viewModel.selectedBlock?.let { block ->
            nombreDetailTextView.text = block.nombre
            descripcionDetailTextView.text = block.descripcion
            Picasso.get().load(block.imageURL).into(imageURLDetailImageView)
        }


        val editButton : Button = view.findViewById<Button?>(R.id.editarLista)
        editButton.setOnClickListener{

        }



        return view
    }
}