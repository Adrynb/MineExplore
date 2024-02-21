package com.example.mineexplore.Fragments

import BlockAdapter
import BlockViewModel
import ItemAdapter

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText

import android.util.Log
import android.widget.Button
import androidx.fragment.app.activityViewModels

import com.example.mineexplore.Block

import com.example.mineexplore.Items
import com.example.mineexplore.Mob

import com.example.mineexplore.R
import com.example.mineexplore.ViewModels.ItemViewModel
import com.example.mineexplore.ViewModels.MobViewModel


class AddFragment : Fragment() {

    private val blockViewModel : BlockViewModel by activityViewModels()
    private val mobViewModel : MobViewModel by activityViewModels()
    private val itemViewModel : ItemViewModel by activityViewModels()
    private lateinit var saveButton : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add, container, false)
        val sourceList = arguments?.getString(ARG_SOURCE_LIST)

        when(sourceList) {
            "blockList" -> {
                val newName: EditText = view.findViewById(R.id.editTextName)
                val newURL : EditText = view.findViewById(R.id.imageURLAdd)
                val newDescription: EditText = view.findViewById(R.id.editTextDescription)
                saveButton = view.findViewById(R.id.saveAll)
                saveButton.setOnClickListener {
                    val newBlock = Block(
                        newName.text.toString(),
                        newURL.text.toString(),
                        newDescription.text.toString()
                    )
                    blockViewModel.addBlock(newBlock)
                    parentFragmentManager.popBackStack()
                }
            }

            "mobList" -> {
                val newName: EditText = view.findViewById(R.id.editTextName)
                val newURL: EditText = view.findViewById(R.id.imageURLAdd)
                val newDescription: EditText = view.findViewById(R.id.editTextDescription)

                saveButton = view.findViewById(R.id.saveAll)
                saveButton.setOnClickListener {
                    val newMob = Mob(
                        newName.text.toString(),
                        newURL.text.toString(),
                        newDescription.text.toString()
                    )
                    mobViewModel.addMob(newMob)
                    fragmentManager?.popBackStack()

                }


            }


            "itemList" -> {

                val newName: EditText = view.findViewById(R.id.editTextName)
                val newURL : EditText = view.findViewById(R.id.imageURLAdd)
                val newDescription: EditText = view.findViewById(R.id.editTextDescription)

                saveButton = view.findViewById(R.id.saveAll)
                saveButton.setOnClickListener {

                    val newItem = Items(newName.text.toString(), newURL.text.toString(), newDescription.text.toString())
                    itemViewModel.addItem(newItem)


                    fragmentManager?.popBackStack()
                }

            }

        }

        return view


    }

    companion object {

            private const val ARG_SOURCE_LIST = "sourceList"
            fun newInstance(sourceList: String) =
                AddFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_SOURCE_LIST, sourceList)
                    }
                }
        }
    }



