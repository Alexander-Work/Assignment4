/*  DetailsActivity.kt
    Alexander Peebles
    200376601
    August 11th 2024
    To Do App - Assignment 4
* */
package ca.georgiancollege.assignment4


import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import ca.georgiancollege.assignment4.databinding.ActivityDetails2Binding
import androidx.lifecycle.ViewModelProvider
import java.util.UUID

class DetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetails2Binding
    private val viewModel: ToDoViewModel by viewModels()
    private lateinit var dataManager: DataManager

    private var toDoItemId: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetails2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        dataManager = DataManager.instance()
        // Retrieve the toDoItemId from the intent extras
        toDoItemId = intent.getStringExtra("toDoItemId")


        // a conditional check to confirm that we are either adding or updating
        if(toDoItemId != null)
        {
            viewModel.loadToDoItemById(toDoItemId!!)
        }
        else
        {
            // removes the delete button from the layout
            binding.deleteButton.visibility = View.GONE
        }

        // Observe the ToDoItem LiveData to update the UI
        viewModel.toDoItem.observe(this) { toDoItem ->
            toDoItem?.let {
                binding.editTextTitle.setText(it.eventTitle)
                binding.editDate.setText(it.date)
                binding.editTextTime.setText(it.time.toString())
                binding.editTextDetails.setText(it.eventDetails)
            }
        }

        binding.saveButton.setOnClickListener {
            saveToDoItem()
        }

        binding.deleteButton.setOnClickListener {
            deleteToDoItem()
        }

        binding.cancelButton.setOnClickListener {
            finish()
        }

        // Observe the selectedDate LiveData to update the UI
        viewModel.selectedDate.observe(this) { formattedDate ->
            binding.editDate.setText(formattedDate) // Update EditText with selected date
        }
        binding.editDate.setOnClickListener {
            viewModel.showDatePicker(supportFragmentManager) // Delegate to ViewModel
        }
    }

    // Function to save or update a ToDoItem
    private fun saveToDoItem()
    {
        val eventTitle = binding.editTextTitle.text.toString()
        val date = binding.editDate.text.toString()
        val time = binding.editTextTime.text.toString()
        val eventDetails = binding.editTextDetails.text.toString()


        if(eventTitle.isNotEmpty())
        {
            Log.d("DetailsActivity", "Saving ToDoItem - ID: $toDoItemId, Title: $eventTitle, Date: $date, Time: $time, Details: $eventDetails")
//            val toDoItem = ToDoItem(id = toDoItemId ?: UUID.randomUUID().toString(), eventTitle = eventTitle, date = date, time = time, eventDetails = eventDetails)
//            viewModel.saveToDoItem(toDoItem)
            Toast.makeText(this, "To Do Saved", Toast.LENGTH_SHORT).show()
            finish()
        }
        else
        {
            Toast.makeText(this, "Please fill out all fields", Toast.LENGTH_SHORT).show()
        }
    }

    // Function to delete a ToDoItem
    private fun deleteToDoItem() {
        toDoItemId?.let { _ ->
            AlertDialog.Builder(this)
                .setTitle("Delete To Do")
                .setMessage("Are you sure you want to delete this To Do?")
                .setPositiveButton("Yes") { _, _ ->
                    viewModel.toDoItem.value?.let {
                        viewModel.deleteToDoItem(it)
                        Toast.makeText(this, "To Do Deleted", Toast.LENGTH_SHORT).show()
                        finish()
                    }
                }
                .setNegativeButton("No", null)
                .show()
        }
    }
}