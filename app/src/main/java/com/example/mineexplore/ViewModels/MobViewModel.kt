package com.example.mineexplore.ViewModels

import androidx.lifecycle.ViewModel
import com.example.mineexplore.Mob

class MobViewModel : ViewModel() {

    private var mobList: MutableList<Mob> = mutableListOf()
    private var _selectedMob: Mob? = null

    val mobs: List<Mob>
        get() = mobList.toList()

    var selectedMob: Mob?
        get() = _selectedMob
        set(value) { _selectedMob = value }

    init {
        mobList.add(
            Mob(
                "Zombie",
                "https://static.wikia.nocookie.net/minecraft_gamepedia/images/8/87/Zombie_JE3_BE2.png",
                "Un enemigo no muerto que vaga por la noche en busca de jugadores"
            )
        )

        mobList.add(
            Mob(
                "Creeper",
                "https://static.wikia.nocookie.net/minecraft_es_gamepedia/images/b/b2/Creeper1.png/revision/latest?cb=20210923172737",
                "Una criatura explosiva que se acerca sigilosamente para explotar"
            )
        )
    }

    fun addMob(mob: Mob) {
        mobList.add(mob)
    }
}
