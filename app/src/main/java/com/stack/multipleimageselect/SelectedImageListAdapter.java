package com.stack.multipleimageselect;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class SelectedImageListAdapter extends RecyclerView.Adapter<SelectedImageListAdapter.ViewHolder>{

    Context context;
    ArrayList<String> stringArrayList;

    public SelectedImageListAdapter(Context context, ArrayList<String> stringArrayList) {
        this.context = context;
        this.stringArrayList = stringArrayList;
    }

    @Override
    public  ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.selected_sell_image_list, viewGroup, false);
        return new ViewHolder(view);
    }
    @Override
    public  void onBindViewHolder(ViewHolder holder, final int position) {


        if (stringArrayList.get (position).equalsIgnoreCase ("add")){

        } else {
            Glide.with(context)
                    .load(stringArrayList.get(position))
                    .placeholder(R.color.purple_700)
                    .into(holder.image);
        }



    }

    @Override
    public int getItemCount() {
        return stringArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        public ViewHolder(View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.image);
        }
    }
}