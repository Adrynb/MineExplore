package com.example.mineexplore.ViewModels

import androidx.lifecycle.ViewModel
import com.example.mineexplore.Items
class ItemViewModel : ViewModel() {
    private var itemList: MutableList<Items> = mutableListOf()
    private var _selectedItem: Items? = null

    val items: List<Items>
        get() = itemList.toList()

    var selectedItem: Items?
        get() = _selectedItem
        set(value) { _selectedItem = value }

    init {
        itemList.add(
            Items(
                "Espada",
                "https://static.wikia.nocookie.net/minecraft_gamepedia/images/4/44/Diamond_Sword_JE3_BE3.png",
                "Una poderosa espada que puede cortar a través de los enemigos con facilidad"
            )
        )

        itemList.add(
            Items(
                "Pico",
                "https://static.wikia.nocookie.net/minecraft_gamepedia/images/e/e7/Diamond_Pickaxe_JE3_BE3.png",
                "Una herramienta esencial para extraer minerales y bloques"
            )
        )
    }

    fun addItem(item: Items) {
        itemList.add(item)
    }
}

