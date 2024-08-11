package ca.georgiancollege.assignment4

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import ca.georgiancollege.assignment4.databinding.ActivityMainBinding
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

//    private val adapter = ToDoAdapter { toDo: ToDoItem ->
//        val intent = Intent(this, DetailsActivity::class.java).apply {
//            putExtra("tvShowId", tvShow.id)
//        }
//        startActivity(intent)
//    }

    override fun onCreate(savedInstanceState: Bundle?) {

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        super.onCreate(savedInstanceState)

        // Initialize Firebase Firestore
        FirebaseFirestore.setLoggingEnabled(true)

        binding.firstRecyclerView.layoutManager = LinearLayoutManager(this)
//        binding.firstRecyclerView.adapter = adapter


        binding.addEventFAB.setOnClickListener {
            val intent = Intent(this, DetailsActivity::class.java)
            startActivity(intent)
        }

        binding.firstRecyclerView.setOnClickListener{
            val intent = Intent(this, DetailsActivity::class.java)
            startActivity(intent)
        }



    }
}