package com.mohamedtaha.imagine.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.mohamedtaha.imagine.R
import com.mohamedtaha.imagine.base.Result
import com.mohamedtaha.imagine.mvp.model.ElarbaoonElnawawyModel
import com.mohamedtaha.imagine.mvp.model.ImageModel
import com.mohamedtaha.imagine.mvp.model.ModelAzkar
import com.mohamedtaha.imagine.mvp.model.ModelSora
import com.mohamedtaha.imagine.ui.home.adapter.AdapterForAzkar
import com.mohamedtaha.imagine.ui.home.adapter.AdapterForPartsAndSwar
import com.mohamedtaha.imagine.ui.home.adapter.ImageAdapter
import com.mohamedtaha.imagine.ui.navigationview.adapter.AdapterElarbaoonElnawawy

object BindingAdapterUtils {
    @JvmStatic
    @BindingAdapter("setRecyclerView")
    fun setRecycleView(recyclerView: RecyclerView, modelSora: List<ModelSora>) {
        val adapter = recyclerView.adapter as AdapterForPartsAndSwar
        adapter.submitList(modelSora)
    }
@JvmStatic
@BindingAdapter("setRecyclerView")
fun setRecyclerViewElnawawy(recyclerView: RecyclerView,elarbaoonElnawawyModel: List<ElarbaoonElnawawyModel>){
    val adapter = recyclerView.adapter as AdapterElarbaoonElnawawy
    adapter.setData(elarbaoonElnawawyModel)
}
    //    @JvmStatic
//    @BindingAdapter("setRecyclerView")
//    fun setRecycleViewForImage(recyclerView: RecyclerView,imageModel: List<ImageModel>){
//        val adapter = recyclerView.adapter as ImageAdapter
//      //  adapter.setData(imageModel)
//    }
    @JvmStatic
    @BindingAdapter("setRecyclerView")
    fun setRecyclerView(recyclerView: RecyclerView, modelAzkar: List<ModelAzkar>) {
        val adapter = recyclerView.adapter as AdapterForAzkar
        adapter.setData(modelAzkar)
    }

    @JvmStatic
    @BindingAdapter("imageUrl")
    fun setImageView(imageView: ImageView, url: String) {
        imageView.load(url) {
            placeholder(R.mipmap.logo)
        }
    }

    @JvmStatic
    @BindingAdapter("imageUrl")
    fun setImageView(imageView: ImageView, url: Int) {
        imageView.load(url) {
            placeholder(R.mipmap.logo)
        }
    }


    @JvmStatic
    @BindingAdapter("layoutRtl")
    fun setLayoutRTL(recyclerView: RecyclerView, boolean: Boolean) {
        val linearLayoutManager: GridLayoutManager =
            object : GridLayoutManager(recyclerView.context, 2) {
                override fun isLayoutRTL(): Boolean {
                    return boolean
                }
            }
        recyclerView.layoutManager = linearLayoutManager
    }
}