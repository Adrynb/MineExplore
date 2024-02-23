package com.example.mineexplore.DetailsFragments

import MobViewModel
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.commit
import androidx.lifecycle.Observer
import com.example.mineexplore.Fragments.BlockFragment
import com.example.mineexplore.Fragments.MobFragment
import com.example.mineexplore.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.squareup.picasso.Picasso

class DetailMob : Fragment() {

    companion object {
        fun newInstance() = DetailMob()
    }

    private val viewModel: MobViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_detail_mob, container, false)

        val nombreMobDetail = view.findViewById<TextView>(R.id.nombreMobDetail)
        val imagenMobDetail = view.findViewById<ImageView>(R.id.imagenMobDetail)
        val descripcionMobDetail = view.findViewById<TextView>(R.id.descriptionMobDetail)

        viewModel.selectedMob.observe(viewLifecycleOwner) { mob ->
            mob?.let {
                nombreMobDetail.text = it.nombre
                descripcionMobDetail.text = it.descripcion
                Picasso.get().load(it.imageURL).into(imagenMobDetail)
            }
        }

        view.findViewById<FloatingActionButton>(R.id.floatingDetailMobButton).setOnClickListener {
           requireActivity().supportFragmentManager.popBackStack()
        }

        val deleteButton : Button = view.findViewById<Button?>(R.id.editarListaMob)
        deleteButton.setOnClickListener{

            viewModel.deleteMob()
            requireActivity().supportFragmentManager.popBackStack()
        }

        return view
    }
}
