package com.example.insta.androidmagicfinder;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.format.Time;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.TextClock;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
public class LockScreenActivity extends AppCompatActivity
{
    String realCode;
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
    TextView TVinfo;
    TextView TVasteriscs;

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
        TVinfo = (TextView) this.findViewById(R.id.TVinfo);
        TVasteriscs = (TextView) this.findViewById(R.id.TVasteriscs);


        hideAllitems();
        IBfullscreen.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) {loadLockScreenImages();}});
        IBback.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) {onClickBack();}});
        TV0.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) {if (TVcode.getText().toString().length()<4) {TVcode.setText(TVcode.getText().toString()+"0");TVasteriscs.setText(TVasteriscs.getText().toString()+"* ");}}});
        TV1.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) {if (TVcode.getText().toString().length()<4){TVcode.setText(TVcode.getText().toString()+"1"); TVasteriscs.setText(TVasteriscs.getText().toString()+"* ");}}});
        TV2.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) {if (TVcode.getText().toString().length()<4){TVcode.setText(TVcode.getText().toString()+"2"); TVasteriscs.setText(TVasteriscs.getText().toString()+"* ");}}});
        TV3.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) {if (TVcode.getText().toString().length()<4){TVcode.setText(TVcode.getText().toString()+"3"); TVasteriscs.setText(TVasteriscs.getText().toString()+"* ");}}});
        TV4.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) {if (TVcode.getText().toString().length()<4){TVcode.setText(TVcode.getText().toString()+"4"); TVasteriscs.setText(TVasteriscs.getText().toString()+"* ");}}});
        TV5.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) {if (TVcode.getText().toString().length()<4){TVcode.setText(TVcode.getText().toString()+"5"); TVasteriscs.setText(TVasteriscs.getText().toString()+"* ");}}});
        TV6.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) {if (TVcode.getText().toString().length()<4){TVcode.setText(TVcode.getText().toString()+"6"); TVasteriscs.setText(TVasteriscs.getText().toString()+"* ");}}});
        TV7.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) {if (TVcode.getText().toString().length()<4){TVcode.setText(TVcode.getText().toString()+"7"); TVasteriscs.setText(TVasteriscs.getText().toString()+"* ");}}});
        TV8.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) {if (TVcode.getText().toString().length()<4){TVcode.setText(TVcode.getText().toString()+"8"); TVasteriscs.setText(TVasteriscs.getText().toString()+"* ");}}});
        TV9.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) {if (TVcode.getText().toString().length()<4){TVcode.setText(TVcode.getText().toString()+"9"); TVasteriscs.setText(TVasteriscs.getText().toString()+"* ");}}});
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
        TVupperline.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);
        TVupperline.setTextColor(Color.WHITE);
        TVinfo.setShadowLayer(3, 3, 3, Color.BLACK);
        TVasteriscs.setShadowLayer(3, 3, 3, Color.BLACK);
    }
    public void onClickOK()
    {
        String pass = TVcode.getText().toString();
        if (pass.length()<4 || pass.contains("5") || pass.contains("6") || pass.contains("7") || pass.contains("8") || pass.contains("9") || pass.contains("0"))
        {
            Intent mainActivity = new Intent(this, MainActivity.class);
            startActivity(mainActivity);
        }
        else
        {
            Intent blockModeActivity = new Intent(this, BlockModeActivity.class);
            blockModeActivity.putExtra("passcode", pass);
            startActivity(blockModeActivity);
        }

    }
    public void loadLockScreenImages()
    {
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN, WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
        this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
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
        //TVcode.setVisibility(View.VISIBLE);
        TCtime.setVisibility(View.VISIBLE);
        TVdate.setVisibility(View.VISIBLE);
        TVupperline.setVisibility(View.VISIBLE);
        TVlowerline.setVisibility(View.VISIBLE);
        TVinfo.setVisibility(View.VISIBLE);
        TVasteriscs.setVisibility(View.VISIBLE);

        SpannableString spannableString2 =  new SpannableString("2 ABC");
        spannableString2.setSpan(new RelativeSizeSpan(0.7f), 1, 5, 0);
        spannableString2.setSpan(new ForegroundColorSpan(Color.LTGRAY), 1, 5, 0);
        TV2.setText(spannableString2);

        SpannableString spannableString3 =  new SpannableString("3 DEF");
        spannableString3.setSpan(new RelativeSizeSpan(0.7f), 1, 5, 0);
        spannableString3.setSpan(new ForegroundColorSpan(Color.LTGRAY), 1, 5, 0);
        TV3.setText(spannableString3);

        SpannableString spannableString4 =  new SpannableString("4 GHI");
        spannableString4.setSpan(new RelativeSizeSpan(0.7f), 1, 5, 0);
        spannableString4.setSpan(new ForegroundColorSpan(Color.LTGRAY), 1, 5, 0);
        TV4.setText(spannableString4);

        SpannableString spannableString5 =  new SpannableString("5 JKL");
        spannableString5.setSpan(new RelativeSizeSpan(0.7f), 1, 5, 0);
        spannableString5.setSpan(new ForegroundColorSpan(Color.LTGRAY), 1, 5, 0);
        TV5.setText(spannableString5);

        SpannableString spannableString6 =  new SpannableString("6 MNO");
        spannableString6.setSpan(new RelativeSizeSpan(0.7f), 1, 5, 0);
        spannableString6.setSpan(new ForegroundColorSpan(Color.LTGRAY), 1, 5, 0);
        TV6.setText(spannableString6);

        SpannableString spannableString7 =  new SpannableString("7 PQRS");
        spannableString7.setSpan(new RelativeSizeSpan(0.7f), 1, 6, 0);
        spannableString7.setSpan(new ForegroundColorSpan(Color.LTGRAY), 1, 6, 0);
        TV7.setText(spannableString7);

        SpannableString spannableString8 =  new SpannableString("8 TUV");
        spannableString8.setSpan(new RelativeSizeSpan(0.7f), 1, 5, 0);
        spannableString8.setSpan(new ForegroundColorSpan(Color.LTGRAY), 1, 5, 0);
        TV8.setText(spannableString8);

        SpannableString spannableString9 =  new SpannableString("9 WXYZ");
        spannableString9.setSpan(new RelativeSizeSpan(0.7f), 1, 6, 0);
        spannableString9.setSpan(new ForegroundColorSpan(Color.LTGRAY), 1, 6, 0);
        TV9.setText(spannableString9);
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
        TVinfo.setVisibility(View.INVISIBLE);
        TVasteriscs.setVisibility(View.INVISIBLE);
    }
    public void onClickBack()
    {

        if (!TVcode.getText().toString().equals(""))
        {
            String text = TVcode.getText().toString();
            String asteriscs = TVasteriscs.getText().toString();
            TVcode.setText(text.substring(0, text.length()-1));
            TVasteriscs.setText(asteriscs.substring(0, asteriscs.length()-1));
        }
    }
}
