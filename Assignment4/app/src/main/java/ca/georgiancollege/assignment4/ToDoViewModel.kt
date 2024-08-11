package ca.georgiancollege.assignment4

import androidx.fragment.app.FragmentManager
import androidx.lifecycle.*
import kotlinx.coroutines.launch
import com.google.android.material.datepicker.MaterialDatePicker
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import java.util.TimeZone

class ToDoViewModel : ViewModel() {

    private val dataManager = DataManager.instance()
    // live data to hold list of tv shows
    private val m_toDoItems = MutableLiveData<List<ToDoItem>>()
    val toDoItems: LiveData<List<ToDoItem>> get() = m_toDoItems
    //live data to hold tv show
    private val m_toDoItem = MutableLiveData<ToDoItem?>()
    val toDoItem: LiveData<ToDoItem?> get() = m_toDoItem

    fun loadAllToDoItems() {
        viewModelScope.launch {
            m_toDoItems.value = dataManager.getAllToDoItems()
        }
    }
    fun loadToDoItemById(id: String) {
        viewModelScope.launch {
            m_toDoItem.value = dataManager.getToDoItemById(id)
        }
    }
    fun saveToDoItem(toDoItem: ToDoItem) {
        viewModelScope.launch {
            if (toDoItem.id.isEmpty()) {
                dataManager.insert(toDoItem)
            } else {
                dataManager.update(toDoItem)
            }
            loadAllToDoItems()
        }
    }
    fun deleteToDoItem(toDoItem: ToDoItem) {
        viewModelScope.launch {
            dataManager.delete(toDoItem)
            loadAllToDoItems()
        }
    }

    //calendar functions
    private val _selectedDate = MutableLiveData<String>()
    val selectedDate: LiveData<String> = _selectedDate

    //date picker fragment
    fun showDatePicker(fragmentManager: FragmentManager) {
        val datePicker = MaterialDatePicker.Builder.datePicker()
            .setTitleText("Select date")
            .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
            .build()

        datePicker.addOnPositiveButtonClickListener { selection ->
            val calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"))
            calendar.timeInMillis = selection
            val formattedDate = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(calendar.time)
            _selectedDate.value = formattedDate // Update LiveData with selected date
        }

        datePicker.show(fragmentManager, "datePicker")
    }
}