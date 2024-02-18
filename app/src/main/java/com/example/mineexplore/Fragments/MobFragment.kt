package com.example.mineexplore.Fragments

import BlockViewModel
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.commit
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.mineexplore.Adaptadores.MobAdapter
import com.example.mineexplore.DetailsFragments.DetailMob
import com.example.mineexplore.MainActivity

import com.example.mineexplore.R
import com.example.mineexplore.ViewModels.MobViewModel


class MobFragment : Fragment() {

    private val viewModel : MobViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val activity = requireActivity() as MainActivity
        activity.disableImageViewClick()
        val view = inflater.inflate(R.layout.fragment_mob, container, false)
        val recyclerView : RecyclerView = view.findViewById(R.id.listaMob)

        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        val adapter = MobAdapter(viewModel)

        adapter.click = {position, mob ->
            viewModel.selectedMob = mob
            parentFragmentManager.commit {
                replace(R.id.fragment_container, DetailMob.newInstance())
                addToBackStack("replacement")
            }
        }

        recyclerView.adapter = adapter
        return view

    }

    companion object {

        @JvmStatic
        fun newInstance() =
            MobFragment().apply {

            }
    }
}