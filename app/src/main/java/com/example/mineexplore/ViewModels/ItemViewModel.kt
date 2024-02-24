package com.example.mineexplore.ViewModels

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mineexplore.DAO.ItemsRepository
import com.example.mineexplore.DAO.MobRepository
import com.example.mineexplore.Items
import com.example.mineexplore.Mob

class ItemViewModel : ViewModel() {

    private lateinit var _context : Context

    private var _itemList: MutableLiveData<List<Items>> = MutableLiveData()
    val items: LiveData<List<Items>> get() = _itemList

    private lateinit var itemsRepository : ItemsRepository

    private val _selectedItem: MutableLiveData<Items?> = MutableLiveData()
    val selectedItem: LiveData<Items?> get() = _selectedItem


    fun initialize(c: Context){
        this._context = c
        this.itemsRepository = ItemsRepository(c)
        _itemList = MutableLiveData()
        this._itemList.value = this.itemsRepository.getAllItems()
    }


    fun addItem(item: Items) {
        itemsRepository.insert(item)
        val currentList = _itemList.value?.toMutableList() ?: mutableListOf()
        currentList.add(item)
        _itemList.value = currentList
    }

    fun deleteItem() {
        _selectedItem.value?.let { selectedItem ->
            itemsRepository.delete(selectedItem)
            val currentList = _itemList.value?.toMutableList() ?: mutableListOf()
            currentList.remove(selectedItem)
            _itemList.value = currentList
            _selectedItem.value = null
        }
    }

    fun setSelectedItem(item: Items) {
        _selectedItem.value = item
    }
}

