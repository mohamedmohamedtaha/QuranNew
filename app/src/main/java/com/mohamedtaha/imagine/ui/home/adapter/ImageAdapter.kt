package com.mohamedtaha.imagine.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.mohamedtaha.imagine.databinding.CustomNameReaderBinding
import com.mohamedtaha.imagine.mvp.model.ImageModel
import com.mohamedtaha.imagine.ui.home.fragment.SoundFragmentDirections

class ImageAdapter :
    RecyclerView.Adapter<ImageAdapter.ViewHolder>() {
    private lateinit var imageModels: List<ImageModel>
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            CustomNameReaderBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val imageModel = imageModels[position]
        holder.binding.imageModel = imageModel
//        Glide.with(holder.binding.textViewShow.context)
//            .load(imageModel.url_image)
//            .override(holder.binding.imageView.width, holder.binding.imageView.height)
//            .into(holder.binding.imageView)
        holder.itemView.setOnClickListener {
            val action = SoundFragmentDirections.actionSoundFragmentToSwipeFragment(
                imageModel.position,
                imageModel.nameShekh!!
            )
            findNavController(holder.itemView).navigate(action)


//            val listSound = Intent(holder.binding.textViewShow.context, ListSoundReader::class.java)
//            val bundle = Bundle()
//            bundle.putString(SHEKH_ID, Gson().toJson(imageModel.position))
//            bundle.putString(SHEKH_NAME, Gson().toJson(imageModel.nameShekh))
//            listSound.putExtras(bundle)
//            holder.binding.textViewShow.context.startActivity(listSound)
//            activity.overridePendingTransition(
//                android.R.anim.slide_in_left,
//                android.R.anim.slide_out_right
//            )
        }
    }

    fun setData(imageModels: List<ImageModel>) {
        this.imageModels = imageModels

    }

    override fun getItemCount(): Int {
        return imageModels.size
    }

    class ViewHolder(val binding: CustomNameReaderBinding) : RecyclerView.ViewHolder(binding.root)
}