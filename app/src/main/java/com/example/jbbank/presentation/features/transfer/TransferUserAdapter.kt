package com.example.jbbank.presentation.features.transfer

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import org.cristovolta.core.domain.model.User
import com.example.jbbank.R
import com.example.jbbank.databinding.ItemTransferUserBinding
import com.squareup.picasso.Picasso

/**
 * Created by João Bosco on 10/11/2023.
 */
class TransferUserAdapter(
    private val userSelected: (User) -> Unit
) : ListAdapter<User, TransferUserAdapter.ViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<User>() {
            override fun areItemsTheSame(
                oldItem: User,
                newItem: User
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: User,
                newItem: User
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemTransferUserBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = getItem(position)

        loadImageUser(holder, user)

        holder.binding.textUserName.text = user.name

        holder.itemView.setOnClickListener {
            userSelected(user)
        }
    }

    private fun loadImageUser(holder: ViewHolder, user: User) {
        if (user.image.isNotEmpty()) {
            Picasso
                .get()
                .load(user.image)
                .centerCrop()
                .into(holder.binding.imgUser)
        } else {
            holder.binding.imgUser.setImageResource(R.drawable.ic_person)
        }
    }

    inner class ViewHolder(val binding: ItemTransferUserBinding) :
        RecyclerView.ViewHolder(binding.root)
}

