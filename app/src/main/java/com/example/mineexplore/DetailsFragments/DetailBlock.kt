import BlockViewModel
import androidx.lifecycle.Observer

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.mineexplore.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.squareup.picasso.Picasso

class DetailBlock : Fragment() {

    private val viewModel: BlockViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_detail_block, container, false)

        val nombreDetailTextView = view.findViewById<TextView>(R.id.nombreBlockDetail)
        val descripcionDetailTextView = view.findViewById<TextView>(R.id.descripcionBlockDetail)
        val imageURLDetailImageView = view.findViewById<ImageView>(R.id.imagenDetailBlock)

        viewModel.selectedBlock.observe(viewLifecycleOwner, Observer { block ->
            block?.let {
                nombreDetailTextView.text = block.nombre
                descripcionDetailTextView.text = block.descripcion
                if (!block.imageURL.isNullOrEmpty()) {
                    Picasso.get().load(block.imageURL).into(imageURLDetailImageView)
                } else {
                    imageURLDetailImageView.setImageResource(com.google.android.material.R.drawable.mtrl_ic_error)
                }
            }
        })

        view.findViewById<FloatingActionButton>(R.id.floatingDetailBlock).setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }

        view.findViewById<Button?>(R.id.editarLista).setOnClickListener{
            viewModel.deleteBlock()
        }

        return view
    }
}

