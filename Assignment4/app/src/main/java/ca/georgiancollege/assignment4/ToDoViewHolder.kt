/*  ToDoViewHolder.kt
    Alexander Peebles
    200376601
    August 11th 2024
    To Do App - Assignment 4
* */
package ca.georgiancollege.assignment4

import androidx.recyclerview.widget.RecyclerView
import ca.georgiancollege.assignment4.databinding.TextRowItemBinding

// view holder for the text row for the recycler view
class ToDoViewHolder(private val binding: TextRowItemBinding) : RecyclerView.ViewHolder(binding.root) {

    //binds to do data to text row within the recycler view
    fun bind(toDoItem: ToDoItem) {
        //event title text row
        binding.eventTitle.text = toDoItem.eventTitle
        // event details text row
        binding.eventDetails.text = toDoItem.eventDetails
        // event date text row
        binding.date.text = toDoItem.date
        // event time text row
        binding.time.text = toDoItem.time.toString()
        // event complete checkbox
        if (toDoItem.complete){
            binding.checkBox.isChecked = true
        }

    }
}
