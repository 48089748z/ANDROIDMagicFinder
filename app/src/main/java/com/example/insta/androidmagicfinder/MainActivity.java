package com.example.insta.androidmagicfinder;
import android.content.DialogInterface;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import com.squareup.picasso.Picasso;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity  implements SensorEventListener
{
    private Integer buttonClicked = 0;
    private float nearestToTarget;
    private boolean numericMode = false;
    private boolean blockMode = false;
    private boolean power = false;
    private boolean firstHit = true;
    private SensorManager sensorManager;
    private float targetDegrees;
    private float currentDegrees;
    private float degreesOnClickPower;
    private long lastMagneticClickMillis = 0;
    private long lastNumericClickMillis = 0;
    ImageButton IBpower;
    ImageButton IBnumeric;
    ImageButton IBmagnetic;
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
    MediaPlayer beep;
    TextView TVmode;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        checkFirstRun();
        beep = MediaPlayer.create(this, R.raw.beep);
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
        IBnumeric = (ImageButton) this.findViewById(R.id.IBnumeric);
        IBmagnetic = (ImageButton) this.findViewById(R.id.IBmagnetic);
        TVmode = (TextView) this.findViewById(R.id.TVmode);
        TVmode.setText("                           .");
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE); //Compass SensorManager
        IBpower.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) {onClickPower();}});
        IBpower.setOnLongClickListener(new View.OnLongClickListener() {@Override public boolean onLongClick(View v) {openDialog();return false;}});
        IB1.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) {buttonClicked=1;}});
        IB2.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) {buttonClicked=2;}});
        IB3.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) {buttonClicked=3;}});
        IB4.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) {buttonClicked=4;}});
        IB5.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) {buttonClicked=5;}});
        IB6.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) {buttonClicked=6;}});
        IB7.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) {buttonClicked=7;}});
        IB8.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) {buttonClicked=8;}});
        IB9.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) {buttonClicked=9;}});
        IB10.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) {buttonClicked=10;}});
        IB11.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) {buttonClicked=11;}});
        IB12.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v){buttonClicked=12;}});
        powerOff();
        IBmagnetic.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v)
        {
            powerOff();
            lastMagneticClickMillis = System.currentTimeMillis();
           if (lastNumericClickMillis+400>System.currentTimeMillis() && lastNumericClickMillis!=0)
           {
               blockMode = true;
               numericMode = false;
               TVmode.setText("                                                       .");
               startBlockMode();
           }
           else
           {

               numericMode = false;
               blockMode = false;
               firstHit = true;
               buttonClicked = 0;
               TVmode.setText("                           .");
           }
        }});
        IBnumeric.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v)
        {
            powerOff();
            lastNumericClickMillis = System.currentTimeMillis();
            if (lastMagneticClickMillis+400>System.currentTimeMillis() && lastMagneticClickMillis!=0)
            {
                blockMode=true;
                numericMode = false;
                TVmode.setText("                                                       .");
                startBlockMode();
            }
            else
            {
                numericMode = true;
                blockMode = false;
                firstHit = true;
                buttonClicked = 0;
                TVmode.setText("                                                                                .");
            }
        }});
    }
    public void onClickPower()
    {
        if (power)
        {
            Picasso.with(this).load(R.drawable.off).fit().into(IBpower); //Change Power ImageButton OFF}
            power = false;
            powerOff();
        }
        else
        {
            Picasso.with(this).load(R.drawable.on).fit().into(IBpower); //Change Power ImageButton ON
            power=true;
            if (numericMode) {startNumericMode();}
            else {startMagneticMode();}
        }
    }
    public void startMagneticMode()
    {
        degreesOnClickPower = currentDegrees;
        if (firstHit && buttonClicked!=0)
        {
            if (buttonClicked==1){onClick1();}
            if (buttonClicked==2){onClick2();}
            if (buttonClicked==3){onClick3();}
            if (buttonClicked==4){onClick4();}
            if (buttonClicked==5){onClick5();}
            if (buttonClicked==6){onClick6();}
            if (buttonClicked==7){onClick7();}
            if (buttonClicked==8){onClick8();}
            if (buttonClicked==9){onClick9();}
            if (buttonClicked==10){onClick10();}
            if (buttonClicked==11){onClick11();}
            if (buttonClicked==12){onClick12();}
        }
        else {onClick3();}
        firstHit = false;
    }
    public void onClick1()
    {
        if (degreesOnClickPower>=330) {targetDegrees = degreesOnClickPower-330;}
        else {targetDegrees=degreesOnClickPower+30;}
    }
    public void onClick2()
    {
        if (degreesOnClickPower>=300) {targetDegrees = degreesOnClickPower-300;}
        else {targetDegrees=degreesOnClickPower+60;}
    }
    public void onClick3()
    {
        if (degreesOnClickPower>=270) {targetDegrees = degreesOnClickPower-270;}
        else {targetDegrees=degreesOnClickPower+90;}
    }
    public void onClick4()
    {
        if (degreesOnClickPower>=240) {targetDegrees = degreesOnClickPower-240;}
        else {targetDegrees=degreesOnClickPower+120;}
    }
    public void onClick5()
    {
        if (degreesOnClickPower>=210) {targetDegrees = degreesOnClickPower-210;}
        else {targetDegrees=degreesOnClickPower+150;}
    }
    public void onClick6()
    {
        if (degreesOnClickPower>=180) {targetDegrees = degreesOnClickPower-180;}
        else {targetDegrees=degreesOnClickPower+180;}
    }
    public void onClick7()
    {
        if (degreesOnClickPower>=150) {targetDegrees = degreesOnClickPower-150;}
        else {targetDegrees=degreesOnClickPower+210;}
    }
    public void onClick8()
    {
        if (degreesOnClickPower>=120) {targetDegrees = degreesOnClickPower-120;}
        else {targetDegrees=degreesOnClickPower+240;}
    }
    public void onClick9()
    {
        if (degreesOnClickPower>=90) {targetDegrees = degreesOnClickPower-90;}
        else {targetDegrees=degreesOnClickPower+270;}
    }
    public void onClick10()
    {
        if (degreesOnClickPower>=60) {targetDegrees = degreesOnClickPower-60;}
        else {targetDegrees=degreesOnClickPower+300;}
    }
    public void onClick11()
    {
        if (degreesOnClickPower>=30) {targetDegrees = degreesOnClickPower-30;}
        else {targetDegrees=degreesOnClickPower+330;}
    }
    public void onClick12() {targetDegrees = degreesOnClickPower;}

    @Override
    public void onSensorChanged(SensorEvent event)
    {
        currentDegrees = Math.round(event.values[0]); //To get the Current Orientation in degrees rounded.
        if (power && !numericMode && !blockMode)
        {
            float a = Math.abs(currentDegrees - targetDegrees);
            float b = Math.abs(360 - Math.abs(currentDegrees - targetDegrees));
            if (a > b) {nearestToTarget = b;}
            else {nearestToTarget = a;}
            bars(nearestToTarget);
        }
    }
    @Override
    protected void onResume()
    {
        super.onResume();
        sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION), SensorManager.SENSOR_DELAY_GAME);  //For the System's Orientation Sensor Registered Listeners
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
        else if (nearestToTarget*2<180){sevenBars();}
        else if (nearestToTarget*2<210) {sixBars();}
        else if (nearestToTarget*2<240) {fiveBars();}
        else if (nearestToTarget*2<270) {fourBars();}
        else if (nearestToTarget*2<300) {threeBars();}
        else if (nearestToTarget*2<330 ) {twoBars();}
        else {oneBar();}
    }
    public void powerOff()
    {
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
        playBeep(350);
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
        playBeep(320);
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
        playBeep(290);
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
        playBeep(260);
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
        playBeep(230);
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
        playBeep(170);
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
        playBeep(140);
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
        playBeep(110);
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
    public void playBeep(long milliseconds)
    {
        if (beep.isPlaying()){}
        else
        {
            try {Thread.sleep(milliseconds);}
            catch (InterruptedException ignored) {}
            beep.start();
        }
    }
    private Integer counter=0;
    public void startNumericMode()
    {
        if (firstHit && buttonClicked!=0)
        {
            if (buttonClicked==1){twelveBars();}
            else{oneBar();}
            counter = buttonClicked;
        }
        else
        {
            if (counter == 1 || counter == 1-2) {twelveBars();}
            else {oneBar();}
        }
        counter--;
        firstHit = false;
    }
    public void startBlockMode()
    {
        Intent lockScreen = new Intent(this, LockScreenActivity.class);
        try {Thread.sleep(1000);} //ESTO HA DE SER 20000 = 20secs
        catch (InterruptedException ignored) {}
        startActivity(lockScreen);
    }
    public void checkFirstRun()
    {
        boolean isFirstRun = getSharedPreferences("PREFERENCE", MODE_PRIVATE).getBoolean("isFirstRun", true);
        if (isFirstRun)
        {
            openDialog();
            getSharedPreferences("PREFERENCE", MODE_PRIVATE).edit().putBoolean("isFirstRun", false).apply();
        }
    }
    public void openDialog()
    {
        new AlertDialog.Builder(this).setTitle("         INFORMATION")
                .setMessage("If you want to open this screen again press and hold the power button for 2 seconds.\n\nOK to open the tutorial of the app.\nCANCEL to exit.")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which)
                    {
                        //OPEN THE GUIDE

                    }
                }).setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {public void onClick(DialogInterface dialog, int which){}}).setIcon(R.drawable.info).show();
    }
}