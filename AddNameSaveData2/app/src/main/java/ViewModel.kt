import androidx.lifecycle.ViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.LiveData

class addnamesavedata2 : ViewModel() {

    private val _names = MutableLiveData<MutableList<String>>()
    val names: LiveData<MutableList<String>>
        get() = _names
    init {
        _names.value = mutableListOf()
    }

    fun addName(name: String) {
        if (name.isNotBlank()) { // Check if the name is not blank
            _names.value?.add(name) // Add the name to the list
            _names.value = _names.value // Trigger LiveData update
        }
    }

}
