package ca.georgiancollege.assignment3

import com.google.firebase.firestore.DocumentId
import com.google.firebase.firestore.IgnoreExtraProperties

@IgnoreExtraProperties
data class ToDoItem(
    @DocumentId val id: String = "",
    val eventTitle: String,
    val eventDetails: String,
    val Date: String,
    val Time: Double
)
{
    // No-argument constructor required for Firestore deserialization
    constructor() : this("", "", "", "", 0.0)
}