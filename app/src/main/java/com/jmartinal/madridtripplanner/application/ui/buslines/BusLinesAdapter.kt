package com.jmartinal.madridtripplanner.application.ui.buslines

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jmartinal.madridtripplanner.databinding.ListItemBusLineBinding
import com.jmartinal.madridtripplanner.domain.BusLine

class BusLinesAdapter(
    private val onClickListener: (BusLine) -> Unit
) : ListAdapter<BusLine, BusLinesAdapter.ViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ListItemBusLineBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.setOnClickListener { onClickListener(getItem(position)) }
        holder.bind(getItem(position))
    }

    inner class ViewHolder(private val binding: ListItemBusLineBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(busLine: BusLine) {
            with(binding) {
                tvLineId.text = busLine.label
                tvLineFrom.text = busLine.nameA
                tvLineTo.text = busLine.nameB
            }
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<BusLine>() {
            override fun areItemsTheSame(oldItem: BusLine, newItem: BusLine): Boolean =
                oldItem.line == newItem.line

            override fun areContentsTheSame(oldItem: BusLine, newItem: BusLine): Boolean =
                oldItem == newItem

        }
    }
}