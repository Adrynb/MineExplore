package com.example.mineexplore.Fragments

import ItemAdapter
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
import com.example.mineexplore.Block
import com.example.mineexplore.DetailsFragments.DetailItem
import com.example.mineexplore.DetailsFragments.DetailMob
import com.example.mineexplore.Items
import com.example.mineexplore.MainActivity
import com.example.mineexplore.R
import com.example.mineexplore.ViewModels.ItemViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton


class ItemFragment : Fragment() {

    private val viewModel: ItemViewModel by activityViewModels()
    private lateinit var itemAdapter : ItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val activity = requireActivity() as MainActivity
        activity.disableImageViewClick()
        val view = inflater.inflate(R.layout.fragment_item, container, false)
        val recyclerView : RecyclerView = view.findViewById(R.id.listaItem)

        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        val adapter = ItemAdapter(viewModel)

        adapter.click = { position, item ->
            viewModel.setSelectedItem(item)
        }

        recyclerView.adapter = adapter

        viewModel.selectedItem.observe(viewLifecycleOwner, Observer { selectedItem ->
            selectedItem?.let {
                parentFragmentManager.beginTransaction().apply {
                    replace(R.id.fragment_container, DetailMob.newInstance())
                    commit()
                }
            }
        })



        view.findViewById<FloatingActionButton>(R.id.floatingItemButton).setOnClickListener {
            parentFragmentManager.beginTransaction().apply {
                replace(R.id.fragment_container, LobbyFragment())
                commit()
            }
        }

        val addBlock: FloatingActionButton = view.findViewById(R.id.floatingAddItem)
        addBlock.setOnClickListener {
            val addFragment = AddFragment.newInstance("itemList")
            parentFragmentManager.beginTransaction().apply {
                replace(R.id.fragment_container, addFragment)
                commit()
            }
        }

        return view
    }



    companion object {
        @JvmStatic
        fun newInstance() =
            ItemFragment().apply {

            }
    }
}