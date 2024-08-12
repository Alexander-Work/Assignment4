/*  DetailsActivity.kt
    Alexander Peebles
    200376601
    August 11th 2024
    To Do App - Assignment 4
* */
package ca.georgiancollege.assignment4


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ca.georgiancollege.assignment4.databinding.ActivityDetails2Binding
import androidx.lifecycle.ViewModelProvider

class DetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetails2Binding
    private lateinit var viewModel: ToDoViewModel // Add ViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetails2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this)[ToDoViewModel::class.java]

//        binding.saveButton.setOnClickListener {
//            saveTVShow()
//        }
//
//        binding.deleteButton.setOnClickListener {
//            deleteTVShow()
//        }

        viewModel.selectedDate.observe(this) { formattedDate ->
            binding.editDate.setText(formattedDate) // Update EditText with selected date
        }
        binding.editDate.setOnClickListener {
            viewModel.showDatePicker(supportFragmentManager) // Delegate to ViewModel
        }

        binding.cancelButton.setOnClickListener {
            finish()
        }


    }

}