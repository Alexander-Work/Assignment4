/*  DataManager.kt
    Alexander Peebles
    200376601
    August 11th 2024
    To Do App - Assignment 4
* */
package ca.georgiancollege.assignment4

import android.content.ContentValues.TAG
import android.util.Log
import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.tasks.await

class DataManager private constructor()
{
    private val db: FirebaseFirestore = Firebase.firestore

    companion object
    {
        @Volatile
        private var m_instance: DataManager? = null

        fun instance(): DataManager
        {
            if(m_instance == null)
            {
                synchronized(this) {
                    if(m_instance == null) {
                        m_instance = DataManager()
                    }
                }
            }
            return m_instance!!
        }
    }

    //control when the database commands are executed
    suspend fun insert(toDoItem: ToDoItem){
        try {
            db.collection("toDoItems").document(toDoItem.id).set(toDoItem).await()
        }catch (e: Exception){

            Log.e(TAG, "Error inserting ToDoItem ${e.message}")
        }
    }

    suspend fun update(toDoItem: ToDoItem){
        try {
            db.collection("toDoItems").document(toDoItem.id).set(toDoItem).await()
        }
        catch (e: Exception){
            Log.e(TAG, "Error inserting ToDoItem ${e.message}")
        }
    }

    suspend fun delete(toDoItem: ToDoItem) {
        try {
            db.collection("toDoItems").document(toDoItem.id).delete().await()
        }
        catch (e: Exception) {
            Log.e(TAG, "Error deleting ToDoItem ${e.message}")
        }
    }

    suspend fun getAllToDoItems(): List<ToDoItem>{
        return try {
            val result = db.collection("toDoItems").get().await()
            result?.toObjects(ToDoItem::class.java) ?: emptyList()
        }
        catch (e: Exception){
            Log.e(TAG, "Error getting all ToDoItems ${e.message}")
            emptyList()
        }
    }

    suspend fun getToDoItemById(id: String): ToDoItem? {
        return try {
            val result = db.collection("toDoItems").document(id).get().await()
            result?.toObject(ToDoItem::class.java)
        }
        catch (e: Exception){
            Log.e(TAG, "Error getting ToDoItem by ID ${e.message}")
            null
        }
    }
}