package edu.huflit.cnpm_th_quanandduy.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.io.IOException;

import edu.huflit.cnpm_th_quanandduy.R;
import edu.huflit.cnpm_th_quanandduy.model.Sach;

public class PlayAudioBookActivity extends AppCompatActivity {

    ImageButton btn_back,btn_Save,btn_previous,btn_replayLeft,btn_replayRight,btn_next,btn_volume,btn_time,btn_play;
    SeekBar seekBar,seekBar_volume;
    TextView tv_time1,tv_time2;
    ImageView img_chiTietSach;
    Sach sach;
    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_audio_book);
        AnhXa();
        sach=(Sach) getIntent().getSerializableExtra("AudioBook");
        Glide.with(PlayAudioBookActivity.this).load(sach.getHinhSach()).into(img_chiTietSach);
        mediaPlayer=new MediaPlayer();

        try {
            mediaPlayer.setDataSource(sach.getMp3());
            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        mediaPlayer.setLooping(true);
        mediaPlayer.seekTo(0);
        mediaPlayer.setVolume(0.5f, 0.5f);
        seekBar_volume.setProgress(50);
        String duraticon=milisecondsToString(mediaPlayer.getDuration());
        String te= String.valueOf(duraticon);
        tv_time2.setText(te);

        seekBar_volume.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                float volume=progress/100f;
                mediaPlayer.setVolume(volume,volume);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        seekBar.setMax(mediaPlayer.getDuration());
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                 if(fromUser)
                 {
                     mediaPlayer.seekTo(progress);
                     seekBar.setProgress(progress);
                 }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (mediaPlayer!=null)
                {
                    if(mediaPlayer.isPlaying())
                    {
                        try {
                            final double current =mediaPlayer.getCurrentPosition();
                            final String el=milisecondsToString((int)current);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    tv_time1.setText(el);
                                    seekBar.setProgress((int) current);
                                }
                            });
                            Thread.sleep(1000);
                        }catch (InterruptedException e)
                        {

                        }
                    }
                }
            }
        }).start();
        btn_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mediaPlayer.isPlaying())
                {
                    mediaPlayer.pause();
                    int changeimg = R.drawable.play_circle_fill;
                        Drawable newImageDrawable = ContextCompat.getDrawable(PlayAudioBookActivity.this, changeimg);
                        btn_play.setImageDrawable(newImageDrawable);
                }else {
                    mediaPlayer.start();
                    int changeimg = R.drawable.pause_circle_fill;
                    Drawable newImageDrawable = ContextCompat.getDrawable(PlayAudioBookActivity.this, changeimg);
                    btn_play.setImageDrawable(newImageDrawable);
                }
            }
        });
        btn_volume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                seekBar_volume.setVisibility(View.VISIBLE);
            }
        });




    }
    public String milisecondsToString(int time)
    {
        String el="";
        int minutes=time/1000/60;
        int secondes=time/1000%60;
        el=minutes+":";
        if(secondes<10)
        {
            el+="0";
        }
        el+=secondes;
        return  el;
    }
    private void  AnhXa()
    {
        btn_back=findViewById(R.id.btn_back);
        btn_Save=findViewById(R.id.btn_Save);
        btn_previous=findViewById(R.id.btn_previous);
        btn_replayLeft=findViewById(R.id.btn_replayLeft);
        btn_replayRight=findViewById(R.id.btn_replayRight);
        btn_next=findViewById(R.id.btn_next);
        btn_volume=findViewById(R.id.btn_volume);
        btn_time=findViewById(R.id.btn_time);
        seekBar=findViewById(R.id.seekBar);
        tv_time1=findViewById(R.id.tv_time1);
        tv_time2=findViewById(R.id.tv_duration);
        img_chiTietSach=findViewById(R.id.img_chiTietSach);
        btn_play=findViewById(R.id.btn_play);
        seekBar_volume=findViewById(R.id.seekBar_volume);
    }
}