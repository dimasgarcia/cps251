package com.mycompany.contacts2025

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.mycompany.contacts2025.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val vm: MainViewModel by viewModels()
    private lateinit var adapter: ContactListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 1) setup RecyclerView & adapter
        adapter = ContactListAdapter { contact ->
            vm.delete(contact.contactId)
            // puts the focus back on name
            binding.etName.requestFocus()
        }
        binding.rvContacts.layoutManager = LinearLayoutManager(this)
        binding.rvContacts.adapter = adapter

        // 2) observe data
        vm.allContacts.observe(this) { list ->
            adapter.submitList(list)
        }
        vm.searchResults.observe(this) { list ->
            if (list.isEmpty()) toast("No matches")
            else adapter.submitList(list)
        }

        // 3) button clicks
        binding.btnAdd.setOnClickListener {
            val name = binding.etName.text.toString().trim()
            val phone = binding.etPhone.text.toString().trim()
            if (name.isEmpty() || phone.isEmpty()) {
                toast("Please enter both name + phone")
            } else {
                vm.insert(name, phone)
                binding.etName.text?.clear()
                binding.etPhone.text?.clear()
                binding.etName.requestFocus()
            }
        }
        binding.btnFind.setOnClickListener {
            vm.find(binding.etName.text.toString().trim())
            binding.etName.requestFocus()
        }
        binding.btnAsc.setOnClickListener {
            adapter.sortAscending()
            binding.etName.requestFocus()
        }
        binding.btnDesc.setOnClickListener {
            adapter.sortDescending()
            binding.etName.requestFocus()
        }
    }

    private fun toast(msg: String) =
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}
