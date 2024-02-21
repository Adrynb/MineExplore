import androidx.lifecycle.ViewModel
import com.example.mineexplore.Block
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
class BlockViewModel : ViewModel() {

    private var blockList: MutableList<Block> = mutableListOf()
    private val _blocksLiveData = MutableLiveData<List<Block>>()
    private val _selectedBlockLiveData = MutableLiveData<Block?>()

    val blocksLiveData: LiveData<List<Block>> = _blocksLiveData
    val selectedBlockLiveData: LiveData<Block?> = _selectedBlockLiveData

    init {
        blockList.add(
            Block(
                "Piedra",
                "https://static.wikia.nocookie.net/minecraft_gamepedia/images/d/d4/Stone.png",
                "Este bloque está en las cuevas, tiene un color grisáceo y tonalidades oscuras"
            )
        )

        blockList.add(
            Block(
                "Madera",
                "https://static.wikia.nocookie.net/minecraft_gamepedia/images/c/c1/Oak_Planks.png",
                "Este bloque aparece en los bosques. Tiene un color marrón, grueso y tonalidades amarillas"
            )
        )

        _blocksLiveData.value = blockList.toList()
    }

    fun addBlock(block: Block) {
        blockList.add(block)
        _blocksLiveData.value = blockList.toList()
    }

    fun setSelectedBlock(block: Block?) {
        _selectedBlockLiveData.value = block
    }

}
