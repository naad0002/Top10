package com.example.top10apps;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;

import static android.content.ContentValues.TAG;

public class FeedEntry {

    private String name;
    private String summary;
    private String artist;
    private String imageUrl;
private Bitmap bitmap;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        Log.d(TAG, "StringToBitMap: ppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppp"+imageUrl);
        this.imageUrl=imageUrl;
       // bitmap= BitmapFactory.decodeStream(imageUrl)
       // this.bitmap =  StringToBitMap(imageUrl);
       // BitmapFactory.decodeFile("R.layout.image");
       // imageUrl;

    }
//    public Bitmap StringToBitMap(String encodedString){
//        try{
//            byte [] encodeByte = Base64.decode(encodedString,Base64.DEFAULT);
//
//            Bitmap bitmap = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
//            Log.d(TAG, "StringToBitMap: ppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppp"+encodedString);
//            return bitmap;
//        }
//        catch(Exception e){
//            e.getMessage();
//            Log.d(TAG, "StringToBitMap:lalalalalalalalalallllllllllllllllllllllllllllllllllllllllllllllllllaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa "+bitmap);
//            return bitmap;
//        }
//    }

// second solution is you can set the path inside decodeFile function
//viewImage.setImageBitmap(BitmapFactory.decodeFile("your iamge path"));

    @Override
    public String toString() {
        return "FeedEntry{" +
                "name=" + name + '\n' +
                ", summary=" + summary + '\n' +
                ", artist=" + artist + '\n' +
",imageUrl="+imageUrl+'\n'+
                '}';
    }
}
