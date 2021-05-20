package com.stack.multipleimageselect;

import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;

import java.util.ArrayList;

public class ImageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private ArrayList<ImageModel> imageList;
    private static OnItemClickListener onItemClickListener;
    ArrayList<String> selectedImageList;

    public ImageAdapter(Context context, ArrayList<ImageModel> imageList,ArrayList<String> selectedImageList) {
        this.context = context;
        this.imageList = imageList;
        this.selectedImageList = selectedImageList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from (parent.getContext ()).inflate (R.layout.image_list, parent, false);
        return new ImageListViewHolder (view);
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        final ImageListViewHolder viewHolder = (ImageListViewHolder) holder;
        Glide.with (context)
                .load (imageList.get (position).getImage ())
                .placeholder (R.color.black)
                .centerCrop ()
                .transition (DrawableTransitionOptions.withCrossFade (500))
                .into (viewHolder.image);

        if (imageList.get(position).isSelected()){
            for (int i = 0; i<selectedImageList.size();i++){
                if (selectedImageList.get (i).equals (imageList.get (position).getImage ())){
                    int count = selectedImageList.size() - i;
                    viewHolder.tvCount.setText(""+(count));
                    viewHolder.tvCount.setVisibility(View.VISIBLE);
                    viewHolder.checkBox.setChecked (true);
                }
            }
        }else {
            viewHolder.checkBox.setChecked (false);
            viewHolder.tvCount.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return imageList.size ();
    }

    public class ImageListViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        CheckBox checkBox;
        TextView tvCount;

        public ImageListViewHolder(View itemView) {
            super (itemView);
            image = itemView.findViewById (R.id.image);
            checkBox = itemView.findViewById (R.id.circle);
            tvCount = itemView.findViewById (R.id.tvCount);
            itemView.setOnClickListener (new View.OnClickListener () {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick (getAdapterPosition (), v);
                }
            });
        }
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(int position, View v);
    }
}