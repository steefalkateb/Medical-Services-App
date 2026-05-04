package com.example.alwadi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.VideoView;

public class About_us extends AppCompatActivity {
    VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setDisplayShowTitleEnabled(false);
//        https://gist.github.com/androidcodehunter/16b83716d80904cfb5913a78adf93bad
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        final Drawable upArrow = ContextCompat.getDrawable(this, R.drawable.abc_ic_ab_back_material);
        upArrow.setColorFilter(ContextCompat.getColor(this, R.color.alwadi), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);
///////////////////////////////////////////////////
//        https://www.tutlane.com/tutorial/android/android-video-player-with-examples

//        https://www.c-sharpcorner.com/article/adding-video-to-an-android-application/
        VideoView videoView = (VideoView) findViewById(R.id.videoView);
        videoView.setVideoPath("android.resource://" + getPackageName() + "/" + R.raw.video_about);

        MediaController mediaController = new MediaController(this);
        //link mediaController to videoView
        mediaController.setAnchorView(videoView);
        //allow mediaController to control our videoView
        videoView.setMediaController(mediaController);
        videoView.start();


//        ProgressBar mProgress = (ProgressBar) findViewById(R.id.progress);
//        mProgress.setVisibility(View.VISIBLE);
//        mProgress.setVisibility(View.INVISIBLE);


//        VideoView videoView = (VideoView) findViewById(R.id.videoView1);
////        MediaController mediaController = new MediaController(this);
//        mediaController.setAnchorView(videoView);
//        videoView.setVisibility(videoView.VISIBLE);
//
//
//        Uri uri = Uri.parse("https://kalansarigroup.com/ALWADI_2021/vedio_about_us/VID-20210707-WA0000.mp4");
//        videoView.setMediaController(mediaController);
//
//        videoView.setVideoURI(uri);
//        videoView.requestFocus();
//        videoView.start();


//        Uri uri = Uri.parse("https://kalansarigroup.com/ALWADI_2021/vedio_about_us/VID-20210707-WA0000.mp4");
//        MediaController mediaController = new MediaController(this);
//        mediaController.setVisibility(View.GONE); // hide progress bar
//        videoView.setMediaController(mediaController);
//        videoView.setVideoURI(uri);
//        videoView.requestFocus();
//        videoView.start();


    }

}