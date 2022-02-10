package com.example.moodme;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ImageFormat;
import android.graphics.Rect;
import android.graphics.YuvImage;
import android.media.Image;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.moodme.EntityClass.UserModel;
import com.example.moodme.databinding.ActivityRecordingsBinding;


import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.FrameGrabber;

import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;

public class RecordingsActivity extends AppCompatActivity {

    ActivityRecordingsBinding binding;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRecordingsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ArrayList<UserModel> list = (ArrayList<UserModel>) DatabaseClass.getDatabase(getApplicationContext()).getDao().getAllData();
        String[] videoName = new String[list.size()];
        String[] duration= new String[list.size()];
        String[] path = new String[list.size()];
        Bitmap[] image = new Bitmap[list.size()];
        for (int i = 0; i < list.size(); i++){
            videoName[i] = list.get(i).getTag();
            Log.d("Video tag", videoName[i]);
            Log.d("Video tag", "LOL");
            duration[i] = list.get(i).getDuration();
            path[i] = list.get(i).getVideo();
            //Log.d("***************", path[i]);
            image[i] = getVideoThumb(path[i]);
        }
        GridAdapter gridAdapter = new GridAdapter(RecordingsActivity.this, videoName, duration, image);
        binding.gridView.setAdapter(gridAdapter);

        binding.gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //Item in grid clicked
            }
        });

    }
    public Bitmap getVideoThumb(String path) {

        MediaMetadataRetriever media = new MediaMetadataRetriever();

        media.setDataSource(this.getApplicationContext(), Uri.parse(path));

        return media.getFrameAtTime();

    }
}
