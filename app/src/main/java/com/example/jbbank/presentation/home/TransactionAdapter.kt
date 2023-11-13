package com.example.jbbank.presentation.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.core.domain.model.Transaction
import com.example.jbbank.databinding.ItemTransactionBinding
import com.example.jbbank.util.GetMask

/**
 * Created by João Bosco on 10/11/2023.
 */
class TransactionAdapter: ListAdapter<Transaction, TransactionAdapter.ViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Transaction>() {
            override fun areItemsTheSame(
                oldItem: Transaction,
                newItem: Transaction
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: Transaction,
                newItem: Transaction
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemTransactionBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val transaction = getItem(position)

        holder.binding.textTransactionTransfer.text = transaction.description
        holder.binding.textTransactionType.text = when (transaction.description) {
            "TRANSFERÊNCIA" -> "T"
            "RECARGA" -> "R"
            "DEPOSITO" -> "D"
            else -> {
                ""
            }
        }
        holder.binding.textTransactionValue.text = GetMask.getFormatValue(transaction.value)
        holder.binding.textTransactionDate.text =
            GetMask.getFormatDate(transaction.date, GetMask.DAY_MONTH_YEAR_HOUR_MINUTE)
    }

    inner class ViewHolder(val binding: ItemTransactionBinding) :
        RecyclerView.ViewHolder(binding.root)
}

