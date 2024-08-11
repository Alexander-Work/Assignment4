package ca.georgiancollege.assignment3


import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import ca.georgiancollege.assignment3.databinding.ActivityDetails2Binding
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.datepicker.MaterialDatePicker
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import java.util.TimeZone

class DetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetails2Binding
    private lateinit var viewModel: DetailsViewModel // Add ViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetails2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this)[DetailsViewModel::class.java]

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