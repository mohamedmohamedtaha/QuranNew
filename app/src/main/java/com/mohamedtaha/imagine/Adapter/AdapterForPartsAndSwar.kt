package com.mohamedtaha.imagine.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.mohamedtaha.imagine.databinding.CustomPartsSwarQuranBinding
import com.mohamedtaha.imagine.mvp.model.ModelSora

class AdapterForPartsAndSwar(
    private val isParts: Boolean,
    private val clickListener: ClickListener?
) : androidx.recyclerview.widget.ListAdapter<ModelSora, AdapterForPartsAndSwar.ViewHolder>(
    DiffCallback
) {
    interface ClickListener {
        fun onClick(view: View?, position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewHolder = ViewHolder(
            CustomPartsSwarQuranBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
        viewHolder.itemView.setOnClickListener {
            val position = viewHolder.adapterPosition
            // clickListener?.onClick(viewHolder, position)
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val modelSora = getItem(position)
        //For change font type
       // holder.bind(modelSora, isParts)
       holder.binding.modelSora = modelSora
        holder.binding.isParts = isParts
    }

    class ViewHolder(val binding: CustomPartsSwarQuranBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(modelSora: ModelSora, isParts: Boolean) {
            if (isParts) {
                binding.TVNameSora.text = modelSora.nameSora
                binding.TVNzolElsora.visibility = View.INVISIBLE
            } else {
              //  binding.TVNameSora.text = modelSora.nameSora
                binding.TVNzolElsora.visibility = View.VISIBLE
              //  binding.TVNzolElsora.text = modelSora.nzolElsora
            }

        }
    }

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