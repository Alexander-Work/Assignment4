/*  MainActivty.kt
    Alexander Peebles
    200376601
    August 11th 2024
    To Do App - Assignment 4
* */
package ca.georgiancollege.assignment4

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import ca.georgiancollege.assignment4.databinding.ActivityMainBinding
import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import java.util.UUID

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

        // Create a new user with a first and last name
        val toDoItem = ToDoItem(id = UUID.randomUUID().toString(), "testing567", "hahah version 2", "567", "1234",  false)
        viewModel.saveToDoItem(toDoItem)


        binding.firstRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.firstRecyclerView.adapter = adapter
        dataManager = DataManager.instance()


        viewModel.toDoItems.observe(this) { toDoItems ->
            adapter.submitList(toDoItems)
        }

        viewModel.loadAllToDoItems()



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