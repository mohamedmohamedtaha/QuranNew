package com.mohamedtaha.imagine.Adapter;

import android.content.Context;
import android.os.Build;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import com.mohamedtaha.imagine.R;
import com.mohamedtaha.imagine.mvp.model.ImageModel;

import java.io.File;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by MANASATT on 24/08/17.
 */

public class RecycleViewReaderAdapter extends RecyclerView.Adapter<RecycleViewReaderAdapter.RecycleViewHolder> {
    private String FILENAME = null;
    private List<ImageModel> responses;
    private ClickListener clickListener;
    private static DownloadMusic downloadMusic;
    Context context;
    RecycleViewHolder viewHolder;

    public RecycleViewReaderAdapter(Context context, List<ImageModel> responses, ClickListener clickListener, DownloadMusic downloadMusic) {
        this.context = context;
        this.responses = responses;
        this.clickListener = clickListener;
        this.downloadMusic = downloadMusic;
    }

    @Override
    public RecycleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View row = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_templete, parent, false);
        RecycleViewHolder holder = new RecycleViewHolder(row);
        row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = holder.getAdapterPosition();
                if (clickListener != null) clickListener.onClick(row, position);


            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(RecycleViewHolder holder, int position) {
        viewHolder = holder;
        ImageModel data = responses.get(holder.getAdapterPosition());
        FILENAME = "/" + data.getName_shekh() + "/";
        File media_path = context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS + FILENAME);
        final File exStore = new File(media_path, data.getName_sora() + ".mp3");
        if (exStore != null && exStore.exists()) {
            viewHolder.IVDownload.setVisibility(View.INVISIBLE);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                holder.text_title.setTextColor(context.getColor(R.color.colorAccent));
            } else {
                holder.text_title.setTextColor(context.getResources().getColor(R.color.colorAccent));
            }
        } else {
            holder.text_title.setTextColor(context.getResources().getColor(android.R.color.black));
            viewHolder.IVDownload.setVisibility(View.VISIBLE);
        }
        holder.text_title.setText(data.getName_sora());

//        holder.IVDownload.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (data != null ){
//                    holder.IVDownload.setVisibility(View.INVISIBLE);
//                    Toast.makeText(context, "id is  : " + data, Toast.LENGTH_SHORT).show();
//                    Log.d("TAG" , "id is  : " + holder.getAdapterPosition());
//                }else {
//                    Toast.makeText(context, "id is not : " +data, Toast.LENGTH_SHORT).show();
//
//                    Log.d("TAG" , "id is not : " + holder.getAdapterPosition());
//
//                }
//
//
//            }
//        });
    }


    @Override
    public int getItemCount() {
        return responses.size();
    }

    public static class RecycleViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.text_title)
        TextView text_title;
        @BindView(R.id.IV_Download)
        ImageView IVDownload;
        private View view;

        public RecycleViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            ButterKnife.bind(this, view);
            IVDownload = (ImageView) view.findViewById(R.id.IV_Download);
            IVDownload.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if (downloadMusic != null) downloadMusic.download(position);
                }
            });
        }
    }

    public interface ClickListener {
        void onClick(View view, int position);
    }

    public interface DownloadMusic {
        void download(int position);
    }
}