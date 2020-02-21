package com.example.top10apps;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


import com.squareup.picasso.Picasso;

import java.util.List;

import static android.content.ContentValues.TAG;

public class FeedAdapter extends ArrayAdapter {
private static final String TAG ="FeedAdapter";
private final int layoutResource;
private final LayoutInflater layoutInnflater;
private List<FeedEntry>applications;


   public FeedAdapter(Context context ,int resource,List<FeedEntry>applications){
        super(context,resource);
        this.layoutResource=resource;
        this.layoutInnflater=LayoutInflater.from(context);
        this.applications=applications;
    }

    @Override
    public int getCount() {
        return applications.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHandler viewHandler;
        if(convertView==null) {
           convertView = layoutInnflater.inflate(layoutResource, parent, false);
           viewHandler=new ViewHandler(convertView);
           convertView.setTag(viewHandler);
        }
else{
    viewHandler=(ViewHandler)convertView.getTag();
        }

            FeedEntry currentApp=applications.get(position);

         viewHandler.summary.setText(currentApp.getSummary());
          viewHandler.title.setText(currentApp.getName());
          viewHandler.artist.setText(currentApp.getArtist());
         //  viewHandler.image.setImageBitmap(currentApp.getImageUrl());
      // Picasso.get().load("").into(viewHandler.image);
      // Picasso.get().load(currentApp.getImageUrl()).into(viewHandler.image);
       Picasso.with(getContext()).load(currentApp.getImageUrl()).into(viewHandler.image);


        return convertView;
    }
}
 class ViewHandler{
    final ImageView image;
  final  TextView summary;
    final  TextView title;
    final  TextView artist;
   // Bitmap bitmap=null;
    ViewHandler(View v) {
        this.image=(ImageView)v. findViewById(R.id.image);
        Log.d(TAG, "ViewHandler:image bitmap "+image);
    this.  summary = (TextView) v.findViewById(R.id.summary);
       this. artist = (TextView)v.findViewById(R.id.author);
        this.title = (TextView) v.findViewById(R.id.title);

    }
        }





