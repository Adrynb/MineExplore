package com.example.mineexplore.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.mineexplore.MainActivity
import com.example.mineexplore.R



class ItemFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val activity = requireActivity() as MainActivity
        activity.disableImageViewClick()

        return inflater.inflate(R.layout.fragment_item, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            ItemFragment().apply {

            }
    }
}