/*  ToDoComparator.kt
    Alexander Peebles
    200376601
    August 11th 2024
    To Do App - Assignment 4
* */
package ca.georgiancollege.assignment4

import androidx.recyclerview.widget.DiffUtil

// compares two events so the list will not display two of the same "id"
class ToDoComparator: DiffUtil.ItemCallback<ToDoItem>()
{
    //checks id
    override fun areItemsTheSame(oldItem: ToDoItem, newItem: ToDoItem): Boolean
    {
        return oldItem.id == newItem.id
    }

    // checks properties
    override fun areContentsTheSame(oldItem: ToDoItem, newItem: ToDoItem): Boolean
    {
        return oldItem == newItem
    }
}