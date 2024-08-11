package ca.georgiancollege.assignment4

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import ca.georgiancollege.assignment4.databinding.TextRowItemBinding


class ToDoAdapter(private val onItemClicked: (ToDoItem) -> Unit) :
    ListAdapter<ToDoItem, ToDoViewHolder>(ToDoComparator()) {

    // Called when the RecyclerView needs a new ViewHolder to represent an item.
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoViewHolder {
        // Inflate the item layout and create a binding object
        val binding = TextRowItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        // Return a new ViewHolder instance with the binding object
        return ToDoViewHolder(binding)
    }

    // Called by the RecyclerView to display the data at the specified position.
    override fun onBindViewHolder(holder: ToDoViewHolder, position: Int) {
        // Get the TVShow item at the given position
        val current = getItem(position)
        // Bind the TVShow item to the ViewHolder
        holder.bind(current)
        // Set a click listener on the itemView to handle item clicks
        holder.itemView.setOnClickListener {
            onItemClicked(current)
        }
    }
}
