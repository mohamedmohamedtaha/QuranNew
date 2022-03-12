package com.mohamedtaha.imagine.adapter

import android.content.Context
import androidx.viewpager.widget.PagerAdapter
import com.mohamedtaha.imagine.mvp.model.ModelAzkar
import android.view.ViewGroup
import android.view.LayoutInflater
import com.mohamedtaha.imagine.R
import android.text.method.ScrollingMovementMethod
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import butterknife.BindView
import android.widget.TextView
import com.mohamedtaha.imagine.libraryTextView.TextViewEx
import butterknife.ButterKnife
import java.util.ArrayList

class AdapterForAzkarSwipe : PagerAdapter {
    var context: Context
    var modelAzkarList: List<ModelAzkar>? = ArrayList()
    var lisenter: showDetail? = null

    constructor(context: Context, modelAzkarList: List<ModelAzkar>?, lisenter: showDetail?) {
        this.context = context
        this.modelAzkarList = modelAzkarList
        this.lisenter = lisenter
    }

    constructor(context: Context, modelAzkarList: List<ModelAzkar>?) {
        this.context = context
        this.modelAzkarList = modelAzkarList
    }

    override fun getCount(): Int {
        return if (modelAzkarList != null && modelAzkarList!!.size >= 0) {
            modelAzkarList!!.size
        } else {
            0
        }
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        //For change font type
//        nyTypeface = Typeface.createFromAsset(context.getAssets(), FONT_TYPE);
//
        val view = LayoutInflater.from(context).inflate(R.layout.custom_azkar_swipe, null)
        val viewHolder = ViewHolder(view)
        val modelSora = modelAzkarList!![position]
        viewHolder.TVNameDescribe!!.text = modelSora.describe_azkar
        //For Scrolling for textView
        viewHolder.TVNameDescribe!!.movementMethod = ScrollingMovementMethod()
        viewHolder.TVNameAzkar!!.text = modelSora.name_azkar
        //        viewHolder.TVNameAzkar
//                .setTypeface(nyTypeface);
        //  Toast.makeText(context, modelSora.getName_azkar() , Toast.LENGTH_SHORT).show();
        //   Toast.makeText(context, "Size: " + modelAzkarList.get(position).getName_azkar() , Toast.LENGTH_SHORT).show();
        viewHolder.TVNameDescribe!!.setOnClickListener {
            if (lisenter != null) lisenter!!.showDetails(
                modelSora
            )
        }
        container.addView(view)
        val animation = AnimationUtils.loadAnimation(context, R.anim.fade_in)
        view.animation = animation
        animation.start()
        animation.start()
        return view
    }

    interface showDetail {
        fun showDetails(modelAzkar: ModelAzkar?)
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    internal class ViewHolder(val binding: View?) {
        @JvmField
        @BindView(R.id.TV_Name_Azkar)
        var TVNameAzkar: TextView? = null

        @JvmField
        @BindView(R.id.TV_Name_Describe)
        var TVNameDescribe: TextViewEx? = null

        init {
            ButterKnife.bind(this, binding!!)
        }
    }
}