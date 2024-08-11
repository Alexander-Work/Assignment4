package ca.georgiancollege.assignment4

import androidx.recyclerview.widget.RecyclerView
import ca.georgiancollege.assignment4.databinding.TextRowItemBinding

// TVShowViewHolder is a ViewHolder for the RecyclerView that displays a single TVShow item.
class ToDoViewHolder(private val binding: TextRowItemBinding) : RecyclerView.ViewHolder(binding.root) {

    //binds to do data to text row within the recycler view
    fun bind(toDoItem: ToDoItem) {
        //event title text row
        binding.eventTitle.text = toDoItem.eventTitle
        // event details text row
        binding.eventDetails.text = toDoItem.eventDetails
        // event date text row
        binding.date.text = toDoItem.Date
        // event time text row
        binding.time.text = toDoItem.Time.toString()
        // event complete checkbox
        if (toDoItem.Complete){
            binding.checkBox.isChecked = true
        }

    }
}
