package com.example.insta.androidmagicfinder;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.Time;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.TextClock;
import android.widget.TextView;

import com.squareup.picasso.Picasso;



public class LockScreenActivity extends AppCompatActivity
{
    Time today = new Time(Time.getCurrentTimezone());
    ImageButton IBfullscreen;
    ImageButton IBback;
    TextView TV0;
    TextView TV1;
    TextView TV2;
    TextView TV3;
    TextView TV4;
    TextView TV5;
    TextView TV6;
    TextView TV7;
    TextView TV8;
    TextView TV9;
    TextView TVok;
    TextView TVcode;
    TextClock TCtime;
    TextView TVdate;
    TextView TVupperline;
    TextView TVlowerline;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_lock_screen);
        today.setToNow();
        IBfullscreen = (ImageButton) this.findViewById(R.id.IBfullscreen);
        IBback = (ImageButton) this.findViewById(R.id.IBback);
        TCtime = (TextClock) this.findViewById(R.id.TCtime);
        TVdate = (TextView) this.findViewById(R.id.TVdate);
        TCtime.setTimeZone(today.getCurrentTimezone());
        TVdate.setText(today.monthDay+"/"+today.month+"/"+today.year);
        TV0 = (TextView) this.findViewById(R.id.TV0);
        TV1 = (TextView) this.findViewById(R.id.TV1);
        TV2 = (TextView) this.findViewById(R.id.TV2);
        TV3 = (TextView) this.findViewById(R.id.TV3);
        TV4 = (TextView) this.findViewById(R.id.TV4);
        TV5 = (TextView) this.findViewById(R.id.TV5);
        TV6 = (TextView) this.findViewById(R.id.TV6);
        TV7 = (TextView) this.findViewById(R.id.TV7);
        TV8 = (TextView) this.findViewById(R.id.TV8);
        TV9 = (TextView) this.findViewById(R.id.TV9);
        TVok = (TextView) this.findViewById(R.id.TVok);
        TVcode = (TextView) this.findViewById(R.id.TVcode);
        TVupperline = (TextView) this.findViewById(R.id.TVupperline);
        TVlowerline = (TextView) this.findViewById(R.id.TVlowerline);

        hideAllitems();
        IBfullscreen.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) {loadLockScreenImages();}});
        IBback.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) {onClickBack();}});
        TV0.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) {if (TVcode.getText().toString().length()<4){TVcode.setText(TVcode.getText().toString()+"0");}}});
        TV1.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) {if (TVcode.getText().toString().length()<4){TVcode.setText(TVcode.getText().toString()+"1");}}});
        TV2.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) {if (TVcode.getText().toString().length()<4){TVcode.setText(TVcode.getText().toString()+"2");}}});
        TV3.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) {if (TVcode.getText().toString().length()<4){TVcode.setText(TVcode.getText().toString()+"3");}}});
        TV4.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) {if (TVcode.getText().toString().length()<4){TVcode.setText(TVcode.getText().toString()+"4");}}});
        TV5.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) {if (TVcode.getText().toString().length()<4){TVcode.setText(TVcode.getText().toString()+"5");}}});
        TV6.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) {if (TVcode.getText().toString().length()<4){TVcode.setText(TVcode.getText().toString()+"6");}}});
        TV7.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) {if (TVcode.getText().toString().length()<4){TVcode.setText(TVcode.getText().toString()+"7");}}});
        TV8.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) {if (TVcode.getText().toString().length()<4){TVcode.setText(TVcode.getText().toString()+"8");}}});
        TV9.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) {if (TVcode.getText().toString().length()<4){TVcode.setText(TVcode.getText().toString()+"9");}}});
        TVok.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) {onClickOK();}});
        TCtime.setShadowLayer(3, 3, 3, Color.BLACK);
        TVdate.setShadowLayer(3, 3, 3, Color.BLACK);
        TV0.setShadowLayer(3, 3, 3, Color.BLACK);
        TV1.setShadowLayer(3, 3, 3, Color.BLACK);
        TV2.setShadowLayer(3, 3, 3, Color.BLACK);
        TV3.setShadowLayer(3, 3, 3, Color.BLACK);
        TV4.setShadowLayer(3, 3, 3, Color.BLACK);
        TV5.setShadowLayer(3, 3, 3, Color.BLACK);
        TV6.setShadowLayer(3, 3, 3, Color.BLACK);
        TV7.setShadowLayer(3, 3, 3, Color.BLACK);
        TV8.setShadowLayer(3, 3, 3, Color.BLACK);
        TV9.setShadowLayer(3, 3, 3, Color.BLACK);
        TVok.setShadowLayer(3, 3, 3, Color.BLACK);
        TVcode.setShadowLayer(3, 3, 3, Color.BLACK);
        TVupperline.setShadowLayer(3, 3, 3, Color.BLACK);
        TVlowerline.setShadowLayer(3, 3, 3, Color.BLACK);
        TVupperline.setPaintFlags(TVupperline.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
    }
    public void onClickOK()
    {

    }
    public void loadLockScreenImages()
    {
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN, WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
        Picasso.with(this).load(R.drawable.lockscreenbackground).fit().into(IBfullscreen);
        IBback.setVisibility(View.VISIBLE);
        TV0.setVisibility(View.VISIBLE);
        TV1.setVisibility(View.VISIBLE);
        TV2.setVisibility(View.VISIBLE);
        TV3.setVisibility(View.VISIBLE);
        TV4.setVisibility(View.VISIBLE);
        TV5.setVisibility(View.VISIBLE);
        TV6.setVisibility(View.VISIBLE);
        TV7.setVisibility(View.VISIBLE);
        TV8.setVisibility(View.VISIBLE);
        TV9.setVisibility(View.VISIBLE);
        TVok.setVisibility(View.VISIBLE);
        TVcode.setVisibility(View.VISIBLE);
        TCtime.setVisibility(View.VISIBLE);
        TVdate.setVisibility(View.VISIBLE);
        TVupperline.setVisibility(View.VISIBLE);
        TVlowerline.setVisibility(View.VISIBLE);

    }
    public void hideAllitems()
    {
        IBback.setVisibility(View.INVISIBLE);
        TV0.setVisibility(View.INVISIBLE);
        TV1.setVisibility(View.INVISIBLE);
        TV2.setVisibility(View.INVISIBLE);
        TV3.setVisibility(View.INVISIBLE);
        TV4.setVisibility(View.INVISIBLE);
        TV5.setVisibility(View.INVISIBLE);
        TV6.setVisibility(View.INVISIBLE);
        TV7.setVisibility(View.INVISIBLE);
        TV8.setVisibility(View.INVISIBLE);
        TV9.setVisibility(View.INVISIBLE);
        TVok.setVisibility(View.INVISIBLE);
        TVcode.setVisibility(View.INVISIBLE);
        TCtime.setVisibility(View.INVISIBLE);
        TVdate.setVisibility(View.INVISIBLE);
        TVupperline.setVisibility(View.INVISIBLE);
        TVlowerline.setVisibility(View.INVISIBLE);
    }
    public void onClickBack()
    {
        if (TVcode.getText().toString().equals("")){}
        else
        {
            String text = TVcode.getText().toString();
            TVcode.setText(text.substring(0, text.length()-1));
        }
    }
}
