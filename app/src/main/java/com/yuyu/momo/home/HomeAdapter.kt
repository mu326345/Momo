package com.yuyu.momo.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.yuyu.momo.data.ResultItem
import com.yuyu.momo.databinding.ItemTitleBinding

class HomeAdapter(private val clickListener: (resultItem: ResultItem) -> Unit) :
    ListAdapter<ResultItem, HomeAdapter.HomeViewHolder>(DiffCallback) {

    class HomeViewHolder(private val binding: ItemTitleBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: ResultItem) {
            binding.data = data
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        return HomeViewHolder(ItemTitleBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
        holder.itemView.setOnClickListener {
            clickListener(item)
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<ResultItem>() {
        override fun areItemsTheSame(oldItem: ResultItem, newItem: ResultItem): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: ResultItem, newItem: ResultItem): Boolean {
            return oldItem == newItem
        }
    }
}