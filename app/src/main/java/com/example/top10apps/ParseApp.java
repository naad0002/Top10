package com.example.top10apps;

import android.graphics.Bitmap;
import android.util.Log;
import android.widget.Switch;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.StringReader;
import java.util.ArrayList;

public class ParseApp {
    private static final String TAG = "ParseApp";
    private ArrayList<FeedEntry> application;

    public ParseApp() {
        this.application = new ArrayList<>();

    }

    public ArrayList<FeedEntry> getApplication() {
        return application;

    }

    public boolean parse(String xmlData) {
        boolean status = true;
        FeedEntry currentRecord=null;
        boolean inEntry = false;
        String textValue = "";
        Bitmap bitmap=null;

        try {
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);
            XmlPullParser xpp = factory.newPullParser();
            xpp.setInput(new StringReader(xmlData));
            int eventType = xpp.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                String tagname=xpp.getName();

                switch (eventType){

                    case XmlPullParser.START_TAG :
                        Log.d(TAG, "parse: Starting tag="+tagname);
                        if("entry".equalsIgnoreCase(tagname)){
                           inEntry=true;
                           currentRecord=new FeedEntry();
                           break;
                        }
                    case XmlPullParser.TEXT :

                       textValue=xpp.getText();
                       break;

                    case XmlPullParser.END_TAG :
                        Log.d(TAG, "parse: Ending tag for "+tagname);
                        if(inEntry) {

                            if ("entry".equalsIgnoreCase(tagname)) {
                                application.add(currentRecord);
                                inEntry = false;
                            } else if ("name".equalsIgnoreCase(tagname)) {
                                currentRecord.setName(textValue);
                            } else if ("artist".equalsIgnoreCase(tagname)) {
                                currentRecord.setArtist(textValue);
                            } else if ("summary".equalsIgnoreCase(tagname)) {
                                currentRecord.setSummary(textValue);
                            } else if ("image".equalsIgnoreCase(tagname)) {
                                currentRecord.setImageUrl(textValue);
                            }
                        }
                        break;
                        default:
                            //
                }
                    eventType=xpp.next();

            }
            for(FeedEntry app:application){
                Log.d(TAG, " *********************");
                Log.d(TAG, app.toString());
            }
        }
        catch (Exception e) {
            status = false;
            e.printStackTrace();
        }

        return status;

    }
}

