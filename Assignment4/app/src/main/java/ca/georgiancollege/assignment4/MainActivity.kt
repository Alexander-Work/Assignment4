/*  MainActivty.kt
    Alexander Peebles
    200376601
    August 11th 2024
    To Do App - Assignment 4
* */
package ca.georgiancollege.assignment4

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import ca.georgiancollege.assignment4.databinding.ActivityMainBinding
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: ToDoViewModel by viewModels()
    private lateinit var dataManager: DataManager


    private val adapter = ToDoAdapter{ toDoItem: ToDoItem ->
        val intent = Intent(this, DetailsActivity::class.java).apply {
            putExtra("toDoItemId", toDoItem.id)
        }
        startActivity(intent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        super.onCreate(savedInstanceState)

        // Initialize Firebase Firestore
        FirebaseFirestore.setLoggingEnabled(true)

        binding.firstRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.firstRecyclerView.adapter = adapter
        dataManager = DataManager.instance()

        viewModel.loadAllToDoItems()
        viewModel.toDoItems.observe(this) { toDoItems ->
            adapter.submitList(toDoItems)
        }


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