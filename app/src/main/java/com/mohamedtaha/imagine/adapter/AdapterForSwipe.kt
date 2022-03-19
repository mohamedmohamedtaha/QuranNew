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
import coil.transform.CircleCropTransformation
import com.google.android.youtube.player.internal.t
import com.mohamedtaha.imagine.R
import com.mohamedtaha.imagine.util.ClickListener
import java.util.ArrayList

class AdapterForSwipe constructor(private val GalImages: List<Int>,private val onClick: ClickListener<Int>): PagerAdapter() {
    override fun getCount(): Int {
        return if ( GalImages.size >= 0) {
            GalImages.size
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
        val imageView = ImageView(container.context)
        imageView.load(GalImages[position]){
         // crossfade(true)
           // transformations(CircleCropTransformation())
        }

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

        container.addView(imageView)
        imageView.scaleType = ImageView.ScaleType.FIT_XY
        imageView.setOnClickListener { onClick.onClick(it, position) }
        //    imageView.setRotationY(180);
        val animation = AnimationUtils.loadAnimation(imageView.context, R.anim.fade_in)
        imageView.animation = animation
        animation.start()
        return imageView
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    companion object {
        private const val FONT_TYPE = "fonts/Aayat-Quraan3.ttf"
    }
}