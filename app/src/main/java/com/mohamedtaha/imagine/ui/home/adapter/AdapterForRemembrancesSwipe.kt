package com.mohamedtaha.imagine.ui.home.adapter

import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.PagerAdapter
import com.mohamedtaha.imagine.R
import com.mohamedtaha.imagine.databinding.CustomAzkarSwipeBinding
import com.mohamedtaha.imagine.mvp.model.ModelAzkar
import com.mohamedtaha.imagine.util.ClickListener

class AdapterForRemembrancesSwipe(
    private val modelRemembrancesList: List<ModelAzkar>,
    private val onClick: ClickListener<Int>
) : PagerAdapter() {

    override fun getCount(): Int {
        return if (modelRemembrancesList.size >= 0) {
            modelRemembrancesList.size
        } else {
            0
        }
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {

        val viewHolder = ViewHolder(
            CustomAzkarSwipeBinding.inflate(
                LayoutInflater.from(container.context), container, false

            )
        )
        val modelSora = modelRemembrancesList[position]
        viewHolder.bind(modelSora, onClick)
        container.addView(viewHolder.binding.root)
        return viewHolder.binding.root
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    internal class ViewHolder(val binding: CustomAzkarSwipeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(modelRemembrances: ModelAzkar, onClick: ClickListener<Int>) {
            binding.modelAzkar = modelRemembrances
            binding.TVNameDescribe.movementMethod = ScrollingMovementMethod()
            val animation = AnimationUtils.loadAnimation(itemView.context, R.anim.fade_in)
            binding.root.animation = animation
            animation.start()
            animation.start()
            binding.TVNameDescribe.setOnClickListener {
                onClick.onClick(it, modelRemembrances.position)
            }
        }
    }
}