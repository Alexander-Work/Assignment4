/*  ToDoItem.kt
    Alexander Peebles
    200376601
    August 11th 2024
    To Do App - Assignment 4
* */
package ca.georgiancollege.assignment4

import com.google.firebase.firestore.DocumentId
import com.google.firebase.firestore.IgnoreExtraProperties

// to do model for database
@IgnoreExtraProperties
data class ToDoItem(
    @DocumentId val id: String = "",
    val eventTitle: String,
    val eventDetails: String,
    val date: String,
    val time: Double,
    val complete: Boolean
)
{
    // No-argument constructor required for Firestore deserialization
    constructor() : this("", "", "", "", 0.0, false)
}