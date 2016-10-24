package com.example.insta.androidmagicfinder;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.TextView;
import com.squareup.picasso.Picasso;

import java.io.IOException;

public class BlockModeActivity extends AppCompatActivity  implements SensorEventListener
{
    private Integer cont =0;
    private Integer timesPowerOn=0;
    private String pass;
    private float nearestToTarget;
    private boolean power = false;
    private SensorManager sensorManager;
    private float targetDegrees;
    private float target1;
    private float target2;
    private float target3;
    private float target4;
    private float currentDegrees;
    private float degreesOnClickPower;
    ImageButton IBpower;
    ImageButton IB1;
    ImageButton IB2;
    ImageButton IB3;
    ImageButton IB4;
    ImageButton IB5;
    ImageButton IB6;
    ImageButton IB7;
    ImageButton IB8;
    ImageButton IB9;
    ImageButton IB10;
    ImageButton IB11;
    ImageButton IB12;
    MediaPlayer beep0;
    MediaPlayer beep;
    MediaPlayer beep2;
    MediaPlayer beep3;
    TextView TVmode;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_block_mode);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE); //Compass SensorManager
        beep0 = MediaPlayer.create(this, R.raw.beep0);
        beep0.setLooping(true);
        beep = MediaPlayer.create(this, R.raw.beep);
        beep2  = MediaPlayer.create(this, R.raw.beep2);
        beep3 = MediaPlayer.create(this, R.raw.beep3);
        IB1 = (ImageButton) this.findViewById(R.id.IB1);
        IB2 = (ImageButton) this.findViewById(R.id.IB2);
        IB3 = (ImageButton) this.findViewById(R.id.IB3);
        IB4 = (ImageButton) this.findViewById(R.id.IB4);
        IB5 = (ImageButton) this.findViewById(R.id.IB5);
        IB6 = (ImageButton) this.findViewById(R.id.IB6);
        IB7 = (ImageButton) this.findViewById(R.id.IB7);
        IB8 = (ImageButton) this.findViewById(R.id.IB8);
        IB9 = (ImageButton) this.findViewById(R.id.IB9);
        IB10 = (ImageButton) this.findViewById(R.id.IB10);
        IB11 = (ImageButton) this.findViewById(R.id.IB11);
        IB12 = (ImageButton) this.findViewById(R.id.IB12);
        IBpower = (ImageButton) this.findViewById(R.id.IBpower);
        TVmode = (TextView) this.findViewById(R.id.TVmode);
        powerOff();
        TVmode.setText("                                                       .");
        IBpower.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) {onClickPower();}});
        pass = getIntent().getExtras().getString("passcode");
    }
    public void onClickPower()
    {
        if (timesPowerOn==0) {degreesOnClickPower = currentDegrees;asignTargets();}
        if (timesPowerOn>3){Intent mainActivity = new Intent(this, MainActivity.class); startActivity(mainActivity);}
        if (power)
        {
            Picasso.with(this).load(R.drawable.off).fit().into(IBpower); //Change Power ImageButton OFF}
            power = false;
            powerOff();
        }
        else
        {
            timesPowerOn = timesPowerOn+1;
            Picasso.with(this).load(R.drawable.on).fit().into(IBpower); //Change Power ImageButton ON
            power=true;
        }
    }
    public void asignTargets()
    {
        for (int x=0; x<pass.length(); x++)
        {
            if (pass.charAt(x) == '1')
            {
                if (x==0){target1=degreesOnClickPower;}
                else if (x==1){ if (degreesOnClickPower>=270) {target1 = degreesOnClickPower-270;} else {target1=degreesOnClickPower+90;}}
                else if (x==2){ if (degreesOnClickPower>=180) {target1 = degreesOnClickPower-180;} else {target1=degreesOnClickPower+180;}}
                else if (x==3){if (degreesOnClickPower>=90) {target1 = degreesOnClickPower-90;} else {target1=degreesOnClickPower+270;}}
            }
        }
        for (int x=0; x<pass.length(); x++)
        {
            if (pass.charAt(x) == '2')
            {
                if (x==0){target2=degreesOnClickPower;}
                else if (x==1){ if (degreesOnClickPower>=270) {target2 = degreesOnClickPower-270;} else {target2=degreesOnClickPower+90;}}
                else if (x==2){ if (degreesOnClickPower>=180) {target2 = degreesOnClickPower-180;} else {target2=degreesOnClickPower+180;}}
                else if (x==3){if (degreesOnClickPower>=90) {target2 = degreesOnClickPower-90;} else {target2=degreesOnClickPower+270;}}
            }
        }
        for (int x=0; x<pass.length(); x++)
        {
            if (pass.charAt(x) == '3')
            {
                if (x==0){target3=degreesOnClickPower;}
                else if (x==1){ if (degreesOnClickPower>=270) {target3 = degreesOnClickPower-270;} else {target3=degreesOnClickPower+90;}}
                else if (x==2){ if (degreesOnClickPower>=180) {target3 = degreesOnClickPower-180;} else {target3=degreesOnClickPower+180;}}
                else if (x==3){if (degreesOnClickPower>=90) {target3 = degreesOnClickPower-90;} else {target3=degreesOnClickPower+270;}}
            }
        }
        for (int x=0; x<pass.length(); x++)
        {
            if (pass.charAt(x) == '4')
            {
                if (x==0){target4=degreesOnClickPower;}
                else if (x==1){ if (degreesOnClickPower>=270) {target4 = degreesOnClickPower-270;} else {target4=degreesOnClickPower+90;}}
                else if (x==2){ if (degreesOnClickPower>=180) {target4 = degreesOnClickPower-180;} else {target4=degreesOnClickPower+180;}}
                else if (x==3){if (degreesOnClickPower>=90) {target4 = degreesOnClickPower-90;} else {target4=degreesOnClickPower+270;}}
            }
        }
    }
    @Override
    public void onSensorChanged(SensorEvent event)
    {
        if (timesPowerOn ==1){targetDegrees=target1;}
        else if (timesPowerOn ==2){targetDegrees=target2;}
        else if (timesPowerOn ==3){targetDegrees=target3;}
        else if (timesPowerOn ==4){targetDegrees=target4;}
        else {}
        currentDegrees = Math.round(event.values[0]); //To get the Current Orientation in degrees rounded.
        float a = Math.abs(currentDegrees - targetDegrees);
        float b = Math.abs(360 - Math.abs(currentDegrees - targetDegrees));
        if (a > b) {nearestToTarget = b;}
        else {nearestToTarget = a;}
        if (power){ bars(nearestToTarget);}
    }
    @Override
    protected void onResume()
    {
        super.onResume();
        sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION), SensorManager.SENSOR_DELAY_FASTEST);  //For the System's Orientation Sensor Registered Listeners
    }
    @Override
    protected void onPause()
    {
        super.onPause();
        sensorManager.unregisterListener(this); //To Stop the listener and Save Battery
    }
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {}
    public void bars(float nearestToTarget)
    {
        if (nearestToTarget*2<30) {twelveBars();}
        else if (nearestToTarget*2<60) {elevenBars();}
        else if (nearestToTarget*2<90) {tenBars();}
        else if (nearestToTarget*2<120) {nineBars();}
        else if (nearestToTarget*2<150) {eightBars();}
        else if (nearestToTarget*2<180) {sevenBars();}
        else if (nearestToTarget*2<210) {sixBars();}
        else if (nearestToTarget*2<240) {fiveBars();}
        else if (nearestToTarget*2<270) {fourBars();}
        else if (nearestToTarget*2<300) {threeBars();}
        else if (nearestToTarget*2<330 ) {twoBars();}
        else {oneBar();}
    }
    public void powerOff()
    {
        stopAndPrepareBeep0();
        stopAndPrepareBeep();
        stopAndPrepareBeep2();
        stopAndPrepareBeep3();
        power = false;
        Picasso.with(this).load(R.drawable.off).fit().into(IBpower);
        Picasso.with(this).load(R.drawable.ql1).fit().into(IB1);
        Picasso.with(this).load(R.drawable.ql2).fit().into(IB2);
        Picasso.with(this).load(R.drawable.ql3).fit().into(IB3);
        Picasso.with(this).load(R.drawable.ql4).fit().into(IB4);
        Picasso.with(this).load(R.drawable.ql5).fit().into(IB5);
        Picasso.with(this).load(R.drawable.ql6).fit().into(IB6);
        Picasso.with(this).load(R.drawable.ql7).fit().into(IB7);
        Picasso.with(this).load(R.drawable.ql8).fit().into(IB8);
        Picasso.with(this).load(R.drawable.ql9).fit().into(IB9);
        Picasso.with(this).load(R.drawable.ql10).fit().into(IB10);
        Picasso.with(this).load(R.drawable.ql11).fit().into(IB11);
        Picasso.with(this).load(R.drawable.ql12).fit().into(IB12);
    }
    public void oneBar()
    {
        playBeep(450);
        Picasso.with(this).load(R.drawable.pl1).fit().into(IB1);
        Picasso.with(this).load(R.drawable.ql2).fit().into(IB2);
        Picasso.with(this).load(R.drawable.ql3).fit().into(IB3);
        Picasso.with(this).load(R.drawable.ql4).fit().into(IB4);
        Picasso.with(this).load(R.drawable.ql5).fit().into(IB5);
        Picasso.with(this).load(R.drawable.ql6).fit().into(IB6);
        Picasso.with(this).load(R.drawable.ql7).fit().into(IB7);
        Picasso.with(this).load(R.drawable.ql8).fit().into(IB8);
        Picasso.with(this).load(R.drawable.ql9).fit().into(IB9);
        Picasso.with(this).load(R.drawable.ql10).fit().into(IB10);
        Picasso.with(this).load(R.drawable.ql11).fit().into(IB11);
        Picasso.with(this).load(R.drawable.ql12).fit().into(IB12);
    }
    public void twoBars()
    {
        playBeep(400);
        Picasso.with(this).load(R.drawable.pl1).fit().into(IB1);
        Picasso.with(this).load(R.drawable.pl2).fit().into(IB2);
        Picasso.with(this).load(R.drawable.ql3).fit().into(IB3);
        Picasso.with(this).load(R.drawable.ql4).fit().into(IB4);
        Picasso.with(this).load(R.drawable.ql5).fit().into(IB5);
        Picasso.with(this).load(R.drawable.ql6).fit().into(IB6);
        Picasso.with(this).load(R.drawable.ql7).fit().into(IB7);
        Picasso.with(this).load(R.drawable.ql8).fit().into(IB8);
        Picasso.with(this).load(R.drawable.ql9).fit().into(IB9);
        Picasso.with(this).load(R.drawable.ql10).fit().into(IB10);
        Picasso.with(this).load(R.drawable.ql11).fit().into(IB11);
        Picasso.with(this).load(R.drawable.ql12).fit().into(IB12);
    }
    public void threeBars()
    {
        playBeep(350);
        Picasso.with(this).load(R.drawable.pl1).fit().into(IB1);
        Picasso.with(this).load(R.drawable.pl2).fit().into(IB2);
        Picasso.with(this).load(R.drawable.pl3).fit().into(IB3);
        Picasso.with(this).load(R.drawable.ql4).fit().into(IB4);
        Picasso.with(this).load(R.drawable.ql5).fit().into(IB5);
        Picasso.with(this).load(R.drawable.ql6).fit().into(IB6);
        Picasso.with(this).load(R.drawable.ql7).fit().into(IB7);
        Picasso.with(this).load(R.drawable.ql8).fit().into(IB8);
        Picasso.with(this).load(R.drawable.ql9).fit().into(IB9);
        Picasso.with(this).load(R.drawable.ql10).fit().into(IB10);
        Picasso.with(this).load(R.drawable.ql11).fit().into(IB11);
        Picasso.with(this).load(R.drawable.ql12).fit().into(IB12);
    }
    public void fourBars()
    {
        playBeep(300);
        Picasso.with(this).load(R.drawable.pl1).fit().into(IB1);
        Picasso.with(this).load(R.drawable.pl2).fit().into(IB2);
        Picasso.with(this).load(R.drawable.pl3).fit().into(IB3);
        Picasso.with(this).load(R.drawable.pl4).fit().into(IB4);
        Picasso.with(this).load(R.drawable.ql5).fit().into(IB5);
        Picasso.with(this).load(R.drawable.ql6).fit().into(IB6);
        Picasso.with(this).load(R.drawable.ql7).fit().into(IB7);
        Picasso.with(this).load(R.drawable.ql8).fit().into(IB8);
        Picasso.with(this).load(R.drawable.ql9).fit().into(IB9);
        Picasso.with(this).load(R.drawable.ql10).fit().into(IB10);
        Picasso.with(this).load(R.drawable.ql11).fit().into(IB11);
        Picasso.with(this).load(R.drawable.ql12).fit().into(IB12);
    }
    public void fiveBars()
    {
        playBeep(250);
        Picasso.with(this).load(R.drawable.pl1).fit().into(IB1);
        Picasso.with(this).load(R.drawable.pl2).fit().into(IB2);
        Picasso.with(this).load(R.drawable.pl3).fit().into(IB3);
        Picasso.with(this).load(R.drawable.pl4).fit().into(IB4);
        Picasso.with(this).load(R.drawable.pl5).fit().into(IB5);
        Picasso.with(this).load(R.drawable.ql6).fit().into(IB6);
        Picasso.with(this).load(R.drawable.ql7).fit().into(IB7);
        Picasso.with(this).load(R.drawable.ql8).fit().into(IB8);
        Picasso.with(this).load(R.drawable.ql9).fit().into(IB9);
        Picasso.with(this).load(R.drawable.ql10).fit().into(IB10);
        Picasso.with(this).load(R.drawable.ql11).fit().into(IB11);
        Picasso.with(this).load(R.drawable.ql12).fit().into(IB12);
    }
    public void sixBars()
    {
        playBeep(200);
        Picasso.with(this).load(R.drawable.pl1).fit().into(IB1);
        Picasso.with(this).load(R.drawable.pl2).fit().into(IB2);
        Picasso.with(this).load(R.drawable.pl3).fit().into(IB3);
        Picasso.with(this).load(R.drawable.pl4).fit().into(IB4);
        Picasso.with(this).load(R.drawable.pl5).fit().into(IB5);
        Picasso.with(this).load(R.drawable.pl6).fit().into(IB6);
        Picasso.with(this).load(R.drawable.ql7).fit().into(IB7);
        Picasso.with(this).load(R.drawable.ql8).fit().into(IB8);
        Picasso.with(this).load(R.drawable.ql9).fit().into(IB9);
        Picasso.with(this).load(R.drawable.ql10).fit().into(IB10);
        Picasso.with(this).load(R.drawable.ql11).fit().into(IB11);
        Picasso.with(this).load(R.drawable.ql12).fit().into(IB12);
    }
    public void sevenBars()
    {
        playBeep(150);
        Picasso.with(this).load(R.drawable.pl1).fit().into(IB1);
        Picasso.with(this).load(R.drawable.pl2).fit().into(IB2);
        Picasso.with(this).load(R.drawable.pl3).fit().into(IB3);
        Picasso.with(this).load(R.drawable.pl4).fit().into(IB4);
        Picasso.with(this).load(R.drawable.pl5).fit().into(IB5);
        Picasso.with(this).load(R.drawable.pl6).fit().into(IB6);
        Picasso.with(this).load(R.drawable.pl7).fit().into(IB7);
        Picasso.with(this).load(R.drawable.ql8).fit().into(IB8);
        Picasso.with(this).load(R.drawable.ql9).fit().into(IB9);
        Picasso.with(this).load(R.drawable.ql10).fit().into(IB10);
        Picasso.with(this).load(R.drawable.ql11).fit().into(IB11);
        Picasso.with(this).load(R.drawable.ql12).fit().into(IB12);
    }
    public void eightBars()
    {
        playBeep(100);
        Picasso.with(this).load(R.drawable.pl1).fit().into(IB1);
        Picasso.with(this).load(R.drawable.pl2).fit().into(IB2);
        Picasso.with(this).load(R.drawable.pl3).fit().into(IB3);
        Picasso.with(this).load(R.drawable.pl4).fit().into(IB4);
        Picasso.with(this).load(R.drawable.pl5).fit().into(IB5);
        Picasso.with(this).load(R.drawable.pl6).fit().into(IB6);
        Picasso.with(this).load(R.drawable.pl7).fit().into(IB7);
        Picasso.with(this).load(R.drawable.pl8).fit().into(IB8);
        Picasso.with(this).load(R.drawable.ql9).fit().into(IB9);
        Picasso.with(this).load(R.drawable.ql10).fit().into(IB10);
        Picasso.with(this).load(R.drawable.ql11).fit().into(IB11);
        Picasso.with(this).load(R.drawable.ql12).fit().into(IB12);
    }
    public void nineBars()
    {
        playBeep(80);
        Picasso.with(this).load(R.drawable.pl1).fit().into(IB1);
        Picasso.with(this).load(R.drawable.pl2).fit().into(IB2);
        Picasso.with(this).load(R.drawable.pl3).fit().into(IB3);
        Picasso.with(this).load(R.drawable.pl4).fit().into(IB4);
        Picasso.with(this).load(R.drawable.pl5).fit().into(IB5);
        Picasso.with(this).load(R.drawable.pl6).fit().into(IB6);
        Picasso.with(this).load(R.drawable.pl7).fit().into(IB7);
        Picasso.with(this).load(R.drawable.pl8).fit().into(IB8);
        Picasso.with(this).load(R.drawable.pl9).fit().into(IB9);
        Picasso.with(this).load(R.drawable.ql10).fit().into(IB10);
        Picasso.with(this).load(R.drawable.ql11).fit().into(IB11);
        Picasso.with(this).load(R.drawable.ql12).fit().into(IB12);
    }
    public void tenBars()
    {
        playBeep(60);
        Picasso.with(this).load(R.drawable.pl1).fit().into(IB1);
        Picasso.with(this).load(R.drawable.pl2).fit().into(IB2);
        Picasso.with(this).load(R.drawable.pl3).fit().into(IB3);
        Picasso.with(this).load(R.drawable.pl4).fit().into(IB4);
        Picasso.with(this).load(R.drawable.pl5).fit().into(IB5);
        Picasso.with(this).load(R.drawable.pl6).fit().into(IB6);
        Picasso.with(this).load(R.drawable.pl7).fit().into(IB7);
        Picasso.with(this).load(R.drawable.pl8).fit().into(IB8);
        Picasso.with(this).load(R.drawable.pl9).fit().into(IB9);
        Picasso.with(this).load(R.drawable.pl10).fit().into(IB10);
        Picasso.with(this).load(R.drawable.ql11).fit().into(IB11);
        Picasso.with(this).load(R.drawable.ql12).fit().into(IB12);
    }
    public void elevenBars()
    {
        playBeep(50);
        Picasso.with(this).load(R.drawable.pl1).fit().into(IB1);
        Picasso.with(this).load(R.drawable.pl2).fit().into(IB2);
        Picasso.with(this).load(R.drawable.pl3).fit().into(IB3);
        Picasso.with(this).load(R.drawable.pl4).fit().into(IB4);
        Picasso.with(this).load(R.drawable.pl5).fit().into(IB5);
        Picasso.with(this).load(R.drawable.pl6).fit().into(IB6);
        Picasso.with(this).load(R.drawable.pl7).fit().into(IB7);
        Picasso.with(this).load(R.drawable.pl8).fit().into(IB8);
        Picasso.with(this).load(R.drawable.pl9).fit().into(IB9);
        Picasso.with(this).load(R.drawable.pl10).fit().into(IB10);
        Picasso.with(this).load(R.drawable.pl11).fit().into(IB11);
        Picasso.with(this).load(R.drawable.ql12).fit().into(IB12);
    }
    public void twelveBars()
    {
        playBeep(0);
        Picasso.with(this).load(R.drawable.pl1).fit().into(IB1);
        Picasso.with(this).load(R.drawable.pl2).fit().into(IB2);
        Picasso.with(this).load(R.drawable.pl3).fit().into(IB3);
        Picasso.with(this).load(R.drawable.pl4).fit().into(IB4);
        Picasso.with(this).load(R.drawable.pl5).fit().into(IB5);
        Picasso.with(this).load(R.drawable.pl6).fit().into(IB6);
        Picasso.with(this).load(R.drawable.pl7).fit().into(IB7);
        Picasso.with(this).load(R.drawable.pl8).fit().into(IB8);
        Picasso.with(this).load(R.drawable.pl9).fit().into(IB9);
        Picasso.with(this).load(R.drawable.pl10).fit().into(IB10);
        Picasso.with(this).load(R.drawable.pl11).fit().into(IB11);
        Picasso.with(this).load(R.drawable.pl12).fit().into(IB12);
    }
    public void stopAndPrepareBeep0(){if (beep0.isPlaying()){beep0.stop(); try {beep0.prepare();} catch (IOException ignored){}}}
    public void stopAndPrepareBeep() {if (beep.isPlaying()){beep.stop(); try {beep.prepare();} catch (IOException ignored){}}}
    public void stopAndPrepareBeep2() {if (beep2.isPlaying()){beep2.stop(); try {beep2.prepare();} catch (IOException ignored){}}}
    public void stopAndPrepareBeep3() {if (beep3.isPlaying()){beep3.stop(); try {beep3.prepare();} catch (IOException ignored){}}}
    public void playBeep(long milliseconds)
    {
        if (milliseconds==0)
        {
            stopAndPrepareBeep0();
            stopAndPrepareBeep();
            stopAndPrepareBeep2();
            if (beep3.isPlaying()){}
            else {beep3.start();}
        }
        else if (milliseconds==50)
        {
            stopAndPrepareBeep0();
            stopAndPrepareBeep();
            stopAndPrepareBeep3();
            if (beep2.isPlaying()){}
            else {beep2.start();}
        }
        else if (milliseconds==450)
        {
            stopAndPrepareBeep();
            stopAndPrepareBeep2();
            stopAndPrepareBeep3();
            if (beep0.isPlaying()){}
            else {beep0.start();}
        }
        else
        {
            stopAndPrepareBeep0();
            stopAndPrepareBeep2();
            stopAndPrepareBeep3();
            if (beep.isPlaying()){}
            else
            {
                try {Thread.sleep(milliseconds);}
                catch (InterruptedException ignored) {}
                beep.start();
            }
        }
    }
    @Override
    protected void onStop()
    {
        super.onStop();
        stopAndPrepareBeep0();
        stopAndPrepareBeep();
        stopAndPrepareBeep2();
        stopAndPrepareBeep3();
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        stopAndPrepareBeep0();
        stopAndPrepareBeep();
        stopAndPrepareBeep2();
        stopAndPrepareBeep3();
    }
}
