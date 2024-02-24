import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.room.DatabaseView
import com.example.mineexplore.Block
import com.example.mineexplore.DAO.BlockRepository
import com.example.mineexplore.DAO.MobRepository


class BlockViewModel : ViewModel() {

    private  lateinit var  _context : Context
    private var _blockList : MutableLiveData<List<Block>> = MutableLiveData()

    val blocks : LiveData<List<Block>> get() = _blockList

    private val _selectedBlock : MutableLiveData<Block?> = MutableLiveData()
    lateinit var blockRepository : BlockRepository


    val selectedBlock : LiveData<Block?> get() = _selectedBlock


    fun initialize(c: Context){
        this._context = c
        this.blockRepository = BlockRepository(c)
        _blockList = MutableLiveData()
        this._blockList.value = this.blockRepository.getAllBlocks()
    }

    fun addBlock(block: Block) {
      blockRepository.insert(block)
        val currentList = _blockList.value?.toMutableList() ?: mutableListOf()
        currentList.add(block)
        _blockList.value = currentList
    }

    fun deleteBlock(){
        _selectedBlock.value?.let { selectedBlock ->
            blockRepository.delete(selectedBlock)
            val currentList = _blockList.value?.toMutableList() ?: mutableListOf()
            currentList.remove(selectedBlock)
            _blockList.value = currentList
            _selectedBlock.value = null
        }
    }

    fun setSelectedBlock(block: Block){
        _selectedBlock.value = block
    }

}
