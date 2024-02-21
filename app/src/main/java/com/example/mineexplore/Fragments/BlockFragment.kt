package com.example.mineexplore.Fragments

import BlockAdapter
import BlockViewModel
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.commit
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mineexplore.DetailsFragments.DetailBlock
import com.example.mineexplore.MainActivity
import com.example.mineexplore.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

class BlockFragment : Fragment() {

    private val viewModel: BlockViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val activity = requireActivity() as MainActivity
        activity.disableImageViewClick()

        val view = inflater.inflate(R.layout.fragment_block, container, false)
        val recyclerView: RecyclerView = view.findViewById(R.id.listaBlock)

        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        val adapter = BlockAdapter(viewModel)


        adapter.click = { position, block ->
            viewModel.selectedBlock = block
            parentFragmentManager.commit {
                replace(R.id.fragment_container, DetailBlock.newInstance())
                addToBackStack("replacement")
            }
        }

        recyclerView.adapter = adapter

        view.findViewById<FloatingActionButton>(R.id.floatingBlockButton).setOnClickListener{
            parentFragmentManager.beginTransaction().apply {
                replace(R.id.fragment_container, LobbyFragment())
                addToBackStack("replacement")
                commit()
            }
        }

        val addBlock: FloatingActionButton = view.findViewById(R.id.floatingAddBlock)
        addBlock.setOnClickListener {
            Log.d("BlockFragment", "Abriendo AddFragment...")
            val addFragment = AddFragment.newInstance("blockList")
            parentFragmentManager.beginTransaction().apply {
                replace(R.id.fragment_container, addFragment)
                addToBackStack("replacement")
                commit()
            }
        }

        return view
    }

    companion object {
        fun newInstance() = BlockFragment()
    }
}
