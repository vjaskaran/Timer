package com.example.eggtimer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    SeekBar seekBar;
    Button button1;
    Button button2;
    Button button3;
    CountDownTimer timer1;
    MediaPlayer mediaPlayer;

    public void silent(View view){
        try {
            mediaPlayer.pause();
            mediaPlayer.prepare();
        }catch(Exception e){
            e.printStackTrace();
            //Toast.makeText(this, "expected error", Toast.LENGTH_SHORT).show();
        }
        //mediaPlayer.release();
        button3.setAlpha(0);
        button3.setEnabled(false);
    }

    public void onClick1(View view){
        button1.setAlpha(1);
        button2.setAlpha(0);
        button1.setEnabled(true);
        button2.setEnabled(false);
        seekBar.setProgress(0);
        seekBar.setEnabled(true);
        textView.setText("0:0");
        timer1.cancel();
        timer1=null;
    }

    public void onClick(View view){
        //Toast.makeText(this, "Button Clicked!", Toast.LENGTH_SHORT).show();
        button1.setAlpha(0);
        button2.setAlpha(1);
        button1.setEnabled(false);
        button2.setEnabled(true);
        seekBar.setEnabled(false);

        int k=seekBar.getProgress();
        timer1=new CountDownTimer(k*1000,1000){
            public void onTick(long millisecLeft){

                int x=(int)millisecLeft/1000;
                int y=x/60;
                int z=x-(60*y);
                textView.setText(Integer.toString(y)+":"+Integer.toString(z));
                seekBar.setProgress((int)millisecLeft/1000);
            }
            public void onFinish(){
                Toast.makeText(MainActivity.this, "Times up!!", Toast.LENGTH_SHORT).show();
                seekBar.setEnabled(true);
                button1.setAlpha(1);
                button2.setAlpha(0);
                button1.setEnabled(true);
                button2.setEnabled(false);

                button3.setAlpha(1);
                button3.setEnabled(true);
                //mediaPlayer.seekTo(0);
                mediaPlayer.start();
            }
        };
        timer1.start();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView=findViewById(R.id.textView2);
        seekBar=findViewById(R.id.seekBar);
        seekBar.setProgress(10);
        button1=findViewById(R.id.button1);
        button2=findViewById(R.id.button2);
        button2.setAlpha(0);
        button2.setEnabled(false);
        button3=findViewById(R.id.button3);
        button3.setAlpha(0);
        button3.setEnabled(false);

        mediaPlayer= MediaPlayer.create(this,R.raw.alarm);


        textView.setText("0 : 10");
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                //Toast.makeText(MainActivity.this, "Value "+i, Toast.LENGTH_SHORT).show();
                int x=i;
                int y=x/60;
                int z=x-(60*y);
                textView.setText(Integer.toString(y)+" : "+Integer.toString(z));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                button2.setAlpha(0);
                button2.setEnabled(false);
                button3.setAlpha(0);
                button3.setEnabled(false);
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }





}
