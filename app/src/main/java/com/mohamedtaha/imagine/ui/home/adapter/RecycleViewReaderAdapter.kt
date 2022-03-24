package com.mohamedtaha.imagine.ui.home.adapter


import android.os.Build
import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mohamedtaha.imagine.R
import com.mohamedtaha.imagine.databinding.RowTemplateBinding
import com.mohamedtaha.imagine.mvp.model.ImageModel
import com.mohamedtaha.imagine.util.ClickListener
import java.io.File

class RecycleViewReaderAdapter(
    private val clickListener: ClickListener<Int>,
    private val downloadMusic: ClickListener<Int>
) : ListAdapter<ImageModel, RecycleViewReaderAdapter.ReaderViewHolder>(ReaderDiffUtil()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReaderViewHolder {
        val viewHolder = ReaderViewHolder(
            RowTemplateBinding.inflate(
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

    override fun onBindViewHolder(holder: ReaderViewHolder, position: Int) {
        val data = getItem(position)
        val fileName = "/" + data.nameShekh + "/"
        val mediaPath =
            holder.itemView.context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS + fileName)
        val exStore = File(mediaPath, data.nameSora + ".mp3")
        holder.bind(data, exStore, downloadMusic)
    }

    class ReaderViewHolder(val binding: RowTemplateBinding) : RecyclerView.ViewHolder(
        binding.root
    ) {

        fun bind(imageModel: ImageModel, exStore: File, downloadMusic: ClickListener<Int>) {
            if (exStore.exists()) {
                binding.IVDownload.visibility = View.INVISIBLE
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    binding.textTitle.setTextColor(
                        ContextCompat.getColor(
                            binding.root.context,
                            R.color.colorAccent
                        )
                    )
                } else {
                    binding.textTitle.setTextColor(
                        ContextCompat.getColor(
                            binding.root.context,
                            R.color.colorAccent
                        )
                    )
                }
            } else {
                binding.textTitle.setTextColor(
                    ContextCompat.getColor(
                        binding.root.context,
                        android.R.color.black
                    )
                )
                binding.IVDownload.visibility = View.VISIBLE
            }

            binding.textTitle.text = imageModel.nameSora
            binding.IVDownload.setOnClickListener {
                val position = adapterPosition
                downloadMusic.onClick(it, position)
            }
        }

    }

    class ReaderDiffUtil : DiffUtil.ItemCallback<ImageModel>() {
        override fun areItemsTheSame(oldItem: ImageModel, newItem: ImageModel): Boolean {
            return oldItem.position == newItem.position
        }

        override fun areContentsTheSame(oldItem: ImageModel, newItem: ImageModel): Boolean {
            return oldItem == newItem
        }

    }
}