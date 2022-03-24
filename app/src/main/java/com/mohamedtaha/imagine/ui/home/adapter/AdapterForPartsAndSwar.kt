package com.mohamedtaha.imagine.ui.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.mohamedtaha.imagine.databinding.CustomPartsSwarQuranBinding
import com.mohamedtaha.imagine.mvp.model.ModelSora
import com.mohamedtaha.imagine.util.ClickListener

class AdapterForPartsAndSwar(
    private val isParts: Boolean,
    private val clickListener: ClickListener<ModelSora>
) : androidx.recyclerview.widget.ListAdapter<ModelSora, AdapterForPartsAndSwar.ViewHolder>(
    DiffCallback
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewHolder = ViewHolder(
            CustomPartsSwarQuranBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
        viewHolder.itemView.setOnClickListener {
            val item = getItem(viewHolder.adapterPosition)
            clickListener.onClick(it,item)
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val modelSora = getItem(position)
        holder.binding.modelSora = modelSora
        holder.binding.isParts = isParts
    }

    inner class ViewHolder(val binding: CustomPartsSwarQuranBinding) :
        RecyclerView.ViewHolder(binding.root)

    companion object {
        val DiffCallback = object : DiffUtil.ItemCallback<ModelSora>() {
            override fun areItemsTheSame(oldItem: ModelSora, newItem: ModelSora): Boolean {
                return oldItem.position == newItem.position
            }

            override fun areContentsTheSame(oldItem: ModelSora, newItem: ModelSora): Boolean {
                return oldItem.nameSora == newItem.nameSora
            }
        }
    }
}