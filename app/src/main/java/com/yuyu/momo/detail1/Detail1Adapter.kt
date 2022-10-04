package com.yuyu.momo.detail1

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.yuyu.momo.data.AResultItem
import com.yuyu.momo.databinding.ItemDetail3Binding

class Detail1Adapter(private val clickListener: (animalItem: AResultItem) -> Unit) :
    ListAdapter<AResultItem, Detail1Adapter.Detail1ViewHolder>(DiffCallback) {

    class Detail1ViewHolder(private val binding: ItemDetail3Binding) : RecyclerView.ViewHolder(binding.root){
        fun bind(data: AResultItem) {
            binding.data = data
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Detail1ViewHolder {
        return Detail1ViewHolder(ItemDetail3Binding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: Detail1ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
        holder.itemView.setOnClickListener {
            clickListener(item)
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<AResultItem>() {
        override fun areItemsTheSame(oldItem: AResultItem, newItem: AResultItem): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: AResultItem, newItem: AResultItem): Boolean {
            return oldItem == newItem
        }
    }
}