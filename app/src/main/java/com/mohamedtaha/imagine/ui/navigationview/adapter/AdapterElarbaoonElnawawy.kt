package com.mohamedtaha.imagine.ui.navigationview.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mohamedtaha.imagine.databinding.CustomElarbaoonElnawawyBinding
import com.mohamedtaha.imagine.mvp.model.ElarbaoonElnawawyModel
import com.mohamedtaha.imagine.ui.navigationview.adapter.AdapterElarbaoonElnawawy.ElarbaoonViewHolder
import com.mohamedtaha.imagine.util.ClickListener

class AdapterElarbaoonElnawawy(
    private val clickListener: ClickListener<ElarbaoonElnawawyModel>
) : RecyclerView.Adapter<ElarbaoonViewHolder>() {
    private lateinit var elnawawyModelsList: List<ElarbaoonElnawawyModel>
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ElarbaoonViewHolder {
        val viewHolder = ElarbaoonViewHolder(
            CustomElarbaoonElnawawyBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
        viewHolder.itemView.setOnClickListener {
            val item = elnawawyModelsList[viewHolder.adapterPosition]
            clickListener.onClick(it, item)
        }
        return viewHolder
    }
    fun setData(elnawawyModelsList: List<ElarbaoonElnawawyModel>){
        this.elnawawyModelsList = elnawawyModelsList
    }

    override fun onBindViewHolder(holder: ElarbaoonViewHolder, position: Int) {
        val elnawawyModel = elnawawyModelsList[position]
        holder.bind(elnawawyModel)
    }

    override fun getItemCount(): Int {
        return elnawawyModelsList.size
    }

    class ElarbaoonViewHolder(val binding: CustomElarbaoonElnawawyBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(elarbaoonElnawawyModel: ElarbaoonElnawawyModel) {
            binding.elarbaoonElnawawy = elarbaoonElnawawyModel
            binding.executePendingBindings()
        }
    }
}