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
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;

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
        int[] keys = new int[list.size()];
        for (int i = 0; i < list.size(); i++){
            keys[i] = list.get(i).getKey();
            Log.d("KEY", String.valueOf(keys[i]));
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
                changeTag(path[i], duration[i], videoName[i], keys[i]);
            }
        });

    }
    public Bitmap getVideoThumb(String path) {

        MediaMetadataRetriever media = new MediaMetadataRetriever();

        media.setDataSource(this.getApplicationContext(), Uri.parse(path));

        return media.getFrameAtTime();

    }

    public void changeTag(String video, String duration, String tag, int key){
        // inflate the layout of the popup window
        LayoutInflater inflater = (LayoutInflater)
                getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.save_tag_popup, null);

        // create the popup window
        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        boolean focusable = true; // lets taps outside the popup also dismiss it
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);

        // show the popup window
        // which view you pass in doesn't matter, it is only used for the window tolken
        popupWindow.showAtLocation(getWindow().getDecorView().getRootView(), Gravity.CENTER, 0, 0);
        // dismiss the popup window when touched
        popupView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                EditText tagField = popupView.findViewById(R.id.tagField);
                if (tagField.getText().equals("")) {
                    Toast.makeText(getApplicationContext(), "All Field is required ....", Toast.LENGTH_SHORT).show();
                } else {
                    String newTag = tagField.getText().toString();
                    DatabaseClass.getDatabase(getApplicationContext()).getDao().updateData(video, duration, newTag, key);
                    finish();
                }
                return true;
            }
        });
    }
}
