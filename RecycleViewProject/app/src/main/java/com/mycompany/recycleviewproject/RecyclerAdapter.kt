package com.mycompany.recycleviewproject

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.mycompany.recycleviewproject.databinding.CardViewBinding

class RecyclerAdapter(
    private val data: DataItems,
    private val randomOrder: List<Int>
) : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: CardViewBinding)
        : RecyclerView.ViewHolder(binding.root) {

        init {
            itemView.setOnClickListener { view ->
                val position = bindingAdapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    Snackbar.make(
                        view,
                        "Clicked item #$position (actual index=${randomOrder[position]})",
                        Snackbar.LENGTH_SHORT
                    ).show()
                }
            }
        }

        fun bind(index: Int) {
            // These are properties defined in DataItems
            binding.itemImage.setImageResource(data.images[index])
            binding.itemTitle.text = data.titles[index]
            binding.itemDetail.text = data.details[index]
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = CardViewBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val actualIndex = randomOrder[position]
        holder.bind(actualIndex)
    }

    override fun getItemCount() = randomOrder.size
}
