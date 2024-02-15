package com.example.mineexplore.Fragments

import BlockAdapter
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
import com.example.mineexplore.DetailsFragments.DetailBlock
import com.example.mineexplore.MainActivity
import com.example.mineexplore.R

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

        return view
    }

    companion object {
        fun newInstance() = BlockFragment()
    }
}
