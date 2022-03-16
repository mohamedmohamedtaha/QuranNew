package com.mohamedtaha.imagine.adapter

import android.content.Context
import androidx.viewpager.widget.PagerAdapter
import android.graphics.Typeface
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import coil.load
import com.mohamedtaha.imagine.R
import java.util.ArrayList

class AdapterForSwipe : PagerAdapter {
    var context: Context
    var lisenter: showDetail? = null
    private var GalImages: ArrayList<Int>? = ArrayList()
    var nyTypeface: Typeface? = null
    private var position = 0

    constructor(context: Context, GalImages: ArrayList<Int>?, lisenter: showDetail?) {
        this.context = context
        this.GalImages = GalImages
        this.lisenter = lisenter
    }

    constructor(context: Context, GalImages: ArrayList<Int>?) {
        this.context = context
        this.GalImages = GalImages
    }

    constructor(context: Context, GalImages: Int) {
        this.context = context
        position = GalImages
    }

    override fun getCount(): Int {
        return if (GalImages != null && GalImages!!.size >= 0) {
            GalImages!!.size
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
//        View view = LayoutInflater.from(context).inflate(R.layout.custom_background_for_ayat, null);
//        viewHolder = new ViewHolder(view);
        val imageView = ImageView(context)
        imageView.load(GalImages?.get(position))

        //  TextView textView = new TextView(context);

//        String colorStart = "<font color='#F05A28'>";
//        String colorEnd = "</font>";
//        //   ModelSora modelSora = GalImages.get(position);
//        String yourText = GalImages.get(position).getName_sora();
//         yourText = yourText.replace("<(>", colorStart);
//        yourText = yourText.replace("<)>", colorEnd);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            viewHolder.tvShowText.setText(Html.fromHtml(yourText));
//        }else {
//            viewHolder.tvShowText.setText(Html.fromHtml(yourText),true);
//
//        }

        //    viewHolder.tvShowText.setText(Html.fromHtml(yourText));


        //  viewHolder.tvShowText
        //      .setTypeface(nyTypeface);

//        Glide.with(context)
//                .load(GalImages.get(position))
//                .into(imageView);
        container.addView(imageView)
        imageView.scaleType = ImageView.ScaleType.FIT_XY
        imageView.setOnClickListener { if (lisenter != null) lisenter!!.showDetails(position) }
        //    imageView.setRotationY(180);
        val animation = AnimationUtils.loadAnimation(context, R.anim.fade_in)
        imageView.animation = animation
        animation.start()

//        Animation animation = AnimationUtils.loadAnimation(context, R.anim.fade_in);
//        viewHolder.tvShowText.setAnimation(animation);
//        animation.start();
        return imageView
    }

    interface showDetail {
        fun showDetails(positon: Int)
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    companion object {
        private const val FONT_TYPE = "fonts/Aayat-Quraan3.ttf"
    }
}