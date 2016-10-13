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
public class BlockModeActivity extends AppCompatActivity  implements SensorEventListener
{
    private String pass;
    private float nearestToTarget;
    private boolean power = false;
    private SensorManager sensorManager;
    private float targetDegrees;
    private float currentDegrees;
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
    MediaPlayer beep;
    TextView TVmode;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_block_mode);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
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
        TVmode = (TextView) this.findViewById(R.id.TVmode);
        TVmode.setText("                                                       .");
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE); //Compass SensorManager
        IBpower.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) {onClickPower();}});
        powerOff();

        pass = getIntent().getExtras().getString("passcode");
        Log.e("Passcode", pass);
       // get extras
                //target degrees = 2314 180 0 90 270

    }
    public void playBeep(final long milliseconds)
    {
        //GOTTA PLAY THE BEEP EACH X MILLISECONDS...
        // beep.start();
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
        }
    }
    @Override
    public void onSensorChanged(SensorEvent event)
    {
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
        playBeep(100);
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
        playBeep(150);
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
        playBeep(200);
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
        playBeep(250);
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
        playBeep(300);
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
        playBeep(350);
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
        playBeep(400);
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
        playBeep(500);
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
        playBeep(600);
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
        playBeep(700);
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
        playBeep(800);
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
        playBeep(900);
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
}
