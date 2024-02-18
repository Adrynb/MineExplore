package com.example.mineexplore.Fragments

import ItemAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.commit
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mineexplore.DetailsFragments.DetailItem
import com.example.mineexplore.MainActivity
import com.example.mineexplore.R
import com.example.mineexplore.ViewModels.ItemViewModel


class ItemFragment : Fragment() {

    private val viewModel: ItemViewModel by activityViewModels()

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

        adapter.click = {position, item ->
            viewModel.selectedItem = item
            parentFragmentManager.commit{
                replace(R.id.fragment_container, DetailItem.newInstance())
                addToBackStack("replacement")
            }
        }

        recyclerView.adapter = adapter

        return view
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            ItemFragment().apply {

            }
    }
}