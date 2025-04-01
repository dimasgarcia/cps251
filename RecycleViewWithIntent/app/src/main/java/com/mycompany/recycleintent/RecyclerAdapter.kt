package com.mycompany.recycleintent

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mycompany.recycleintent.databinding.CardViewBinding
import com.google.android.material.snackbar.Snackbar

class RecyclerAdapter(
    private val data: DataItems
) : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    // I can store a local random order for demo
    private val itemIndices = (data.titles.indices).toList().shuffled()

    inner class ViewHolder(private val binding: CardViewBinding)
        : RecyclerView.ViewHolder(binding.root) {

        init {
            // Set the click listener on the card item
            itemView.setOnClickListener { v ->
                // Retrieve the index we stored in the hidden text or a field in the adapter.
                val hiddenIndexStr = binding.itemIndexHidden.text.toString()
                val actualIndex = hiddenIndexStr.toIntOrNull() ?: return@setOnClickListener

                // Build an Intent to go to SecondActivity
                val i = android.content.Intent(v.context, SecondActivity::class.java)
                // Pass the item index so SecondActivity knows which item we clicked
                i.putExtra("INDEX_CLICKED", actualIndex)

                // I can pass it with:
                // ContextCompat.startActivity(v.context, i, null)
                // or just v.context.startActivity(i)
                androidx.core.content.ContextCompat.startActivity(v.context, i, null)
            }
        }

        fun bind(positionInShuffledList: Int) {
            // positionInShuffledList is an index into itemIndices
            val realIndex = itemIndices[positionInShuffledList]

            // Fill the hidden text with realIndex
            binding.itemIndexHidden.text = realIndex.toString()

            // Set the card’s image, title, detail
            binding.itemImage.setImageResource(data.images[realIndex])
            binding.itemTitle.text = data.titles[realIndex]
            binding.itemDetail.text = data.details[realIndex]
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = CardViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int = data.titles.size
}
