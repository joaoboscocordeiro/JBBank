package com.example.jbbank.presentation.home

import android.content.Context
import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import org.cristovolta.core.domain.enum.TransactionOperation
import org.cristovolta.core.domain.enum.TransactionType
import org.cristovolta.core.domain.model.Transaction
import com.example.jbbank.R
import com.example.jbbank.databinding.ItemTransactionBinding
import com.example.jbbank.util.GetMask

/**
 * Created by João Bosco on 10/11/2023.
 */
class TransactionAdapter(
    private val context: Context,
    private val transactionSelected: (Transaction) -> Unit
) : ListAdapter<Transaction, TransactionAdapter.ViewHolder>(DIFF_CALLBACK) {

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

        transaction.operation?.let {
            holder.binding.textTransactionTransfer.text = TransactionOperation.getOperation(it)

            holder.binding.textTransactionType.text = TransactionType.getType(it).toString()
            holder.binding.textTransactionType.backgroundTintList =
                if (transaction.type == TransactionType.CASH_IN) {
                    ColorStateList.valueOf(ContextCompat.getColor(context, R.color.color_cash_in))
                } else {
                    ColorStateList.valueOf(ContextCompat.getColor(context, R.color.color_cash_out))
                }
        }

        holder.binding.textTransactionValue.text = GetMask.getFormatValue(transaction.amount)
        holder.binding.textTransactionDate.text =
            GetMask.getFormatDate(transaction.date, GetMask.DAY_MONTH_YEAR_HOUR_MINUTE)

        holder.itemView.setOnClickListener {
            transactionSelected(transaction)
        }
    }

    inner class ViewHolder(val binding: ItemTransactionBinding) :
        RecyclerView.ViewHolder(binding.root)
}

