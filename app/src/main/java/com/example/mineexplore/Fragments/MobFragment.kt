package com.example.mineexplore.Fragments

import BlockViewModel
import MobViewModel
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.commit
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.mineexplore.Adaptadores.MobAdapter
import com.example.mineexplore.DetailsFragments.DetailMob
import com.example.mineexplore.MainActivity
import com.example.mineexplore.R
import com.google.android.material.floatingactionbutton.FloatingActionButton


class MobFragment : Fragment() {

    private val viewModel: MobViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val activity = requireActivity() as MainActivity
        activity.disableImageViewClick()
        val view = inflater.inflate(R.layout.fragment_mob, container, false)
        val recyclerView: RecyclerView = view.findViewById(R.id.listaMob)

        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        val adapter = MobAdapter(viewModel)

        adapter.click = { position, mob ->
            viewModel.setSelectedMob(mob)
        }

        viewModel.selectedMob.observe(viewLifecycleOwner, Observer { selectedMob ->
            selectedMob?.let {
                parentFragmentManager.beginTransaction().apply {
                    replace(R.id.fragment_container, DetailMob.newInstance())
                    commit()
                }
            }
        })



        recyclerView.adapter = adapter

        view.findViewById<FloatingActionButton>(R.id.floatingMobButton).setOnClickListener {
            parentFragmentManager.beginTransaction().apply {
                replace(R.id.fragment_container, LobbyFragment())
                commit()
            }
        }

        val addMob: FloatingActionButton = view.findViewById(R.id.floatingAddMob)
        addMob.setOnClickListener {
            val addFragment = AddFragment.newInstance("mobList")
            parentFragmentManager.beginTransaction().apply {
                replace(R.id.fragment_container, addFragment)
                commit()
            }
        }

        return view
    }

    companion object {
        @JvmStatic
        fun newInstance() = MobFragment()
    }
}
