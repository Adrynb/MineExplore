package com.example.mineexplore.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.example.mineexplore.MainActivity
import com.example.mineexplore.R


class LobbyFragment : Fragment() {

    private lateinit var buttonItem: Button
    private lateinit var buttonMob: Button
    private lateinit var buttonBlock: Button
    private lateinit var imageView: ImageView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_lobby, container, false)

        buttonItem = view.findViewById(R.id.btnItems)
        buttonBlock = view.findViewById(R.id.btnBlocks)
        buttonMob = view.findViewById(R.id.btnEnemies)

        imageView = requireActivity().findViewById(R.id.imageViewMineExplore)

        (requireActivity() as MainActivity).disableImageViewClick()

        buttonItem.setOnClickListener {
            fragmentManager?.beginTransaction()?.apply {
                replace(R.id.fragment_container, ItemFragment())
                addToBackStack("replacement")
                commit()
            }
        }

        buttonBlock.setOnClickListener {
            fragmentManager?.beginTransaction()?.apply {
                replace(R.id.fragment_container, BlockFragment())
                addToBackStack("replacement")
                commit()
            }
        }

        buttonMob.setOnClickListener {
            fragmentManager?.beginTransaction()?.apply {
                replace(R.id.fragment_container, MobFragment())
                addToBackStack("replacement")
                commit()
            }
        }

        return view
    }

    override fun onResume() {
        super.onResume()

        (requireActivity() as MainActivity).disableImageViewClick()
    }

    override fun onPause() {
        super.onPause()
        // Habilitar la imagen al pausar el fragmento
        (requireActivity() as MainActivity).enableImageViewClick()
    }

    override fun onStop() {
        super.onStop()
        // Habilitar la imagen al detener el fragmento
        (requireActivity() as MainActivity).enableImageViewClick()
    }
}
