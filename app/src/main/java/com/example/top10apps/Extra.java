package com.example.top10apps;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class Extra extends AppCompatActivity implements View.OnClickListener {
  static String url;

private static final String TAG="TAGGGGG";
//    public String  getUrl(){
//        return url;
//    }
//
//     public void setUrl(String url){
//      this.url=url;
//  }

//  public String fetchurl(){
//
//        return "http://ax.itunes.apple.com/WebObjects/MZStoreServices.woa/ws/RSS/topMovies/xml";
//  }





    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()) {

            case R.id.button1:
              url=("http://ax.itunes.apple.com/WebObjects/MZStoreServices.woa/ws/RSS/topMovies/xml");
                Intent inl1=new Intent(Extra.this,MainActivity.class);

                startActivity(inl1);
                break;

            case R.id.button2:
            this.url=("http://ax.itunes.apple.com/WebObjects/MZStoreServices.woa/ws/RSS/topTvSeasons/xml");
                Intent in12=new Intent(Extra.this,MainActivity.class);
                startActivity(in12);
                break;

            case R.id.button3:
                this.url=("http://ax.itunes.apple.com/WebObjects/MZStoreServices.woa/ws/RSS/topsongs/limit=10/xml");
                Intent inl3=new Intent(Extra.this,MainActivity.class);
                startActivity(inl3);
                break;
            case R.id.button4:
                this.url=(" http://ax.itunes.apple.com/WebObjects/MZStoreServices.woa/ws/RSS/toppaidapplications/limit=25/xml");
                Intent inl4=new Intent(Extra.this,MainActivity.class);
                startActivity(inl4);
                break;
        }
        intent=new Intent(this,MainActivity.class);
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.extra_layout);


        Button btnmovie=(Button)findViewById(R.id.button1);
        Button btnseries=(Button)findViewById(R.id.button2);
        Button btnsongs=(Button)findViewById(R.id.button3);
        Button btnapps=(Button)findViewById(R.id.button4);

        btnmovie.setOnClickListener(this);
        btnseries.setOnClickListener(this);
        btnsongs.setOnClickListener(this);
        btnapps.setOnClickListener(this);
    }
}
