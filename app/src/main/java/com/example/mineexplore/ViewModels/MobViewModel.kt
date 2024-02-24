import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mineexplore.DAO.MobRepository
import com.example.mineexplore.Mob

class MobViewModel : ViewModel() {
    private lateinit var _context: Context
    private var _mobList: MutableLiveData<List<Mob>> = MutableLiveData()
    lateinit var mobRepository: MobRepository
    val mobs: LiveData<List<Mob>> get() = _mobList

    private val _selectedMob: MutableLiveData<Mob?> = MutableLiveData()
    val selectedMob: LiveData<Mob?> get() = _selectedMob


    fun initialize(c: Context){
        this._context = c
        this.mobRepository = MobRepository(c)
        _mobList = MutableLiveData()
        this._mobList.value = this.mobRepository.getAllMobs()
    }


    fun addMob(mob: Mob) {
        mobRepository.insert(mob)
        val currentList = _mobList.value?.toMutableList() ?: mutableListOf()
        currentList.add(mob)
        _mobList.value = currentList
    }

    fun deleteMob() {
        _selectedMob.value?.let { selectedMob ->
            mobRepository.delete(selectedMob)
            val currentList = _mobList.value?.toMutableList() ?: mutableListOf()
            currentList.remove(selectedMob)
            _mobList.value = currentList
            _selectedMob.value = null
        }
    }


    fun setSelectedMob(mob: Mob) {
        _selectedMob.value = mob
    }


}
