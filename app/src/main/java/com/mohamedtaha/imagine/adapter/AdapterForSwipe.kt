package com.mohamedtaha.imagine.adapter

import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter
import coil.load
import com.mohamedtaha.imagine.R
import com.mohamedtaha.imagine.util.ClickListener

class AdapterForSwipe constructor(
    private val GalImages: List<Int>,
    private val onClick: ClickListener<Int>
) : PagerAdapter() {
    override fun getCount(): Int {
        return if (GalImages.size >= 0) {
            GalImages.size
        } else {
            0
        }
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val imageView = ImageView(container.context)
        imageView.rotationY = 180F
        imageView.load(GalImages[position])
        container.addView(imageView)
        imageView.scaleType = ImageView.ScaleType.FIT_XY
        imageView.setOnClickListener { onClick.onClick(it, position) }
        val animation = AnimationUtils.loadAnimation(imageView.context, R.anim.fade_in)
        imageView.animation = animation
        animation.start()
        return imageView
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }
}