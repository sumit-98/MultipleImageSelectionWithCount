package com.stack.multipleimageselect;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.view.View;

import java.io.File;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    int SELECT_IMAGES = 1000;
    ArrayList<String> selectedImageList;
    RecyclerView selectedImageRecyclerView;
    SelectedImageListAdapter selectedImageAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        selectedImageRecyclerView = findViewById(R.id.selected_recycler_view);
        selectedImageList = new ArrayList<>();
        Intent i = new Intent(MainActivity.this, ImagesActivity.class);
        startActivityForResult(i, SELECT_IMAGES);



    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            Uri contentURI = data.getData();


            if (resultCode == RESULT_CANCELED) {
                return;
            }

            if (requestCode == SELECT_IMAGES && resultCode == Activity.RESULT_OK) {
                selectedImageList = (ArrayList<String>) data.getSerializableExtra("all_path");
                setSelectedImageList();
            }
        }
    }

    public void setSelectedImageList() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        selectedImageRecyclerView.setLayoutManager(layoutManager);
        selectedImageRecyclerView.setNestedScrollingEnabled(false);
        selectedImageAdapter = new SelectedImageListAdapter(this, selectedImageList);
        selectedImageRecyclerView.setAdapter(selectedImageAdapter);
    }
}