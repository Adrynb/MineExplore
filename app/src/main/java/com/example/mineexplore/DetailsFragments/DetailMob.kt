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
import com.example.mineexplore.R
import com.example.mineexplore.ViewModels.MobViewModel
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

        viewModel.selectedMob?.let { mob ->
            nombreMobDetail.text = mob.nombre
            descripcionMobDetail.text  = mob.descripcion
            Picasso.get().load(mob.imageURL).into(imagenMobDetail)


        }

        val editButton : Button = view.findViewById<Button?>(R.id.editarListaMob)
        editButton.setOnClickListener{

        }

        return view
    }
}