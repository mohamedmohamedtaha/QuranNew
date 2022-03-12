package com.mohamedtaha.imagine.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mohamedtaha.imagine.databinding.CustomAzkarBinding
import com.mohamedtaha.imagine.mvp.model.ModelAzkar
import com.mohamedtaha.imagine.util.ClickListener

class AdapterForAzkar(
    private val modelAzkars: List<ModelAzkar>,
    private val clickListener: ClickListener
) :
    RecyclerView.Adapter<AdapterForAzkar.AzkarViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AzkarViewHolder {
        val viewHolder = AzkarViewHolder(
            CustomAzkarBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
        viewHolder.itemView.setOnClickListener {
            val position = viewHolder.adapterPosition
            clickListener.onClick(it, position)
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: AzkarViewHolder, position: Int) {
        val azkar = modelAzkars[position]
        holder.binding.modelAzkar = azkar
    }

    override fun getItemCount(): Int {
        return modelAzkars.size
    }

    class AzkarViewHolder(val binding: CustomAzkarBinding) : RecyclerView.ViewHolder(binding.root)
}