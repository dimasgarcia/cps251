package com.mycompany.coroutinesv2

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mycompany.coroutinesv2.databinding.CardViewBinding

class RecyclerAdapter(
    private val items: List<String>
) : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: CardViewBinding)
        : RecyclerView.ViewHolder(binding.root) {

        fun bind(text: String) {
            binding.itemText.text = text
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = CardViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size
}
