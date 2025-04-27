package com.mycompany.contacts2025

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mycompany.contacts2025.databinding.ItemContactBinding

class ContactListAdapter(
    private val onDelete: (Contact) -> Unit
) : RecyclerView.Adapter<ContactListAdapter.VH>() {

    private var items = listOf<Contact>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val binding = ItemContactBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return VH(binding)
    }

    override fun onBindViewHolder(holder: VH, position: Int) =
        holder.bind(items[position])

    override fun getItemCount() = items.size

    fun submitList(list: List<Contact>) {
        items = list
        notifyDataSetChanged()
    }

    fun sortAscending() {
        items = items.sortedBy { it.contactName }
        notifyDataSetChanged()
    }

    fun sortDescending() {
        items = items.sortedByDescending { it.contactName }
        notifyDataSetChanged()
    }

    inner class VH(private val b: ItemContactBinding) :
        RecyclerView.ViewHolder(b.root) {
        fun bind(c: Contact) {
            b.itemTitle.text = c.contactName
            b.itemDetail.text = c.contactPhone
            b.deleteButton.setOnClickListener { onDelete(c) }
        }
    }
}
