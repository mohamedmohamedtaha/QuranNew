package com.mohamedtaha.imagine.adapter


import android.os.Build
import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mohamedtaha.imagine.R
import com.mohamedtaha.imagine.adapter.RecycleViewReaderAdapter.ReaderViewHolder
import com.mohamedtaha.imagine.databinding.RowTemplateBinding
import com.mohamedtaha.imagine.mvp.model.ImageModel
import com.mohamedtaha.imagine.util.ClickListener
import java.io.File

class RecycleViewReaderAdapter(
    private val clickListener: ClickListener<Int>,
    private val downloadMusic: ClickListener<Int>
) : ListAdapter<ImageModel, ReaderViewHolder>(ReaderDiffUtil()) {
    private var FILENAME: String? = null
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
        FILENAME = "/" + data.nameShekh + "/"
        val media_path =
            holder.itemView.context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS + FILENAME)
        val exStore = File(media_path, data.nameSora + ".mp3")
        holder.bind(data, exStore, downloadMusic)
    }

    class ReaderViewHolder(val binding: RowTemplateBinding) : RecyclerView.ViewHolder(
        binding.root
    ) {
        fun bind(imageModel: ImageModel, exStore: File, downloadMusic: ClickListener<Int>) {
            if (exStore.exists()) {
                binding.IVDownload.visibility = View.INVISIBLE
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    binding.textTitle.setTextColor(binding.root.context.getColor(R.color.colorAccent))
                } else {
                    binding.textTitle.setTextColor(binding.root.context.resources.getColor(R.color.colorAccent))
                }
            } else {
                binding.textTitle.setTextColor(binding.root.context.resources.getColor(android.R.color.black))
                binding.IVDownload.visibility = View.VISIBLE
            }

            binding.textTitle.text = imageModel.nameSora
            binding.IVDownload.setOnClickListener {
                val position = adapterPosition
                downloadMusic.onClick(it, position)
            }
        }

    }

    interface DownloadMusic {
        fun download(position: Int)
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

