package edu.huflit.cnpm_th_quanandduy.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import edu.huflit.cnpm_th_quanandduy.R;

public class PlayAudioBookActivity extends AppCompatActivity {

    ImageButton btn_back,btn_Save,btn_previous,btn_replayLeft,btn_replayRight,btn_next,btn_volume,btn_time;
    SeekBar seekBar;
    TextView tv_time1,tv_time2;
    ImageView img_chiTietSach;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_audio_book);
        AnhXa();
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
        tv_time2=findViewById(R.id.tv_time2);
        img_chiTietSach=findViewById(R.id.img_chiTietSach);
    }
}