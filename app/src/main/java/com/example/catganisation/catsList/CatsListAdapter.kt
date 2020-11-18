package com.example.catganisation.catsList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.catganisation.databinding.BreedsCatItemBinding
import com.example.catganisation.model.Breed

class CatsListAdapter(private val clickListener: ListItemListener) :
    ListAdapter<Breed, CatsListAdapter.ViewHolder>(TaskItemsDiffCallback()) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position)!!, clickListener)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    fun getWordAtPosition(position: Int): Breed? {
        return getItem(position)
    }

    class ViewHolder private constructor(val binding: BreedsCatItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Breed, clickListener: ListItemListener) {
            binding.item = item
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = BreedsCatItemBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}

class TaskItemsDiffCallback : DiffUtil.ItemCallback<Breed>() {

    override fun areItemsTheSame(oldItem: Breed, newItem: Breed): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Breed, newItem: Breed): Boolean {
        return oldItem == newItem
    }
}

class ListItemListener(val clickListener: (breed: Breed) -> Unit) {
    fun onClick(breed: Breed) = clickListener(breed)
}
