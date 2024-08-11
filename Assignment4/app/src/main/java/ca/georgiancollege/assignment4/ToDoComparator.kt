package ca.georgiancollege.assignment4

import androidx.recyclerview.widget.DiffUtil

// This is a utility class that helps the Recycler Adapter determine
// how to efficiently update the list when the data changes
class ToDoComparator: DiffUtil.ItemCallback<ToDoItem>()
{
    // This method checks if two items represent the same entity by comparing their IDs.
    override fun areItemsTheSame(oldItem: ToDoItem, newItem: ToDoItem): Boolean
    {
        return oldItem.id == newItem.id
    }

    // this method checks if the contents of two items are the same by comparing their properties.
    override fun areContentsTheSame(oldItem: ToDoItem, newItem: ToDoItem): Boolean
    {
        return oldItem == newItem
    }
}