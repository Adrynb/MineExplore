import androidx.lifecycle.ViewModel
import com.example.mineexplore.Block

class BlockViewModel : ViewModel() {

    private var blockList: MutableList<Block> = mutableListOf()
    private var _selectedBlock: Block? = null

    val blocks: List<Block>
        get() = blockList.toList()

    var selectedBlock: Block?
        get() = _selectedBlock
        set(value) { _selectedBlock = value }

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
    }

    fun addBlock(block: Block) {
        blockList.add(block)
    }
}