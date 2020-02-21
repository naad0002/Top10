package com.example.top10apps;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    private static  final String  TAG ="MainActivity";
   private ListView lists;
private TextView summary;
private TextView Title;
  //  String urls=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lists = (ListView) findViewById(R.id.xmlListView);
        Log.d(TAG, "onCreate: starting Asynctask");
      // DownloadData downloadData = new DownloadData();
       // downloadData.execute("http://ax.itunes.apple.com/WebObjects/MZStoreServices.woa/ws/RSS/topMovies/xml");

        Extra ex =new Extra();
       // String fetch =f.getUrl();
        Log.d(TAG, "onCreate: done fetched"+ex.url);
        downloadURL(ex.url);
        summary = (TextView) findViewById(R.id.summary);
        Title = (TextView) findViewById(R.id.title);

    }

//    @Override
//    protected void onSaveInstanceState(Bundle outState) {
//        outState.putString(STATE_URL , feedUrl);
//        super.onSaveInstanceState(outState);
//
//}
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater i=getMenuInflater();
        i.inflate(R.menu.feed_menu,menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        String url=null;
        switch (item.getItemId()) {
            case R.id.mainmenu:
              
                Toast.makeText(this, "main screen selected", Toast.LENGTH_SHORT).show();
                break;
//            case R.id.filter:
//                Toast.makeText(this, "filter option selected", Toast.LENGTH_LONG).show();
//                break;
            case R.id.Topseries:
                Toast.makeText(this, "Top series filter option selected", Toast.LENGTH_LONG).show();
               url="http://ax.itunes.apple.com/WebObjects/MZStoreServices.woa/ws/RSS/topTvSeasons/xml";
                break;
            case R.id.Topsongs:
                Toast.makeText(this, "Top songs filter option selected", Toast.LENGTH_LONG).show();
                url="http://ax.itunes.apple.com/WebObjects/MZStoreServices.woa/ws/RSS/topsongs/limit=10/xml";
                break;
                default:
                    return super.onOptionsItemSelected(item);
        }
        downloadURL(url);
        return true;
    }
    private void downloadURL(String URL){
        DownloadData downloadData = new DownloadData();

        downloadData.execute(URL);


    }
    private class DownloadData extends AsyncTask<String, Void, String> {
private static  final String  TAG ="DownloadData";

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.d(TAG, "onPostExecute: parameter is "+s);
            ParseApp pa=new ParseApp();
            pa.parse(s);
//            ArrayAdapter<FeedEntry> arrayAdapter=new ArrayAdapter<FeedEntry>(MainActivity.this,R.layout.list_item,pa.getApplication());
//            lists.setAdapter(arrayAdapter);
              FeedAdapter feedAdapter=new FeedAdapter(MainActivity.this,R.layout.details,pa.getApplication());
            lists.setAdapter(feedAdapter);
        }

        @Override
        protected String doInBackground(String... strings) {

            Log.d(TAG, "doInBackground: starts  " + strings[0]);
            String rssfeed = downloadXML(strings[0]);
            if (rssfeed == null) {

                Log.d(TAG, "doInBackground: Error downloading");
            }
            return rssfeed;
        }

        private String downloadXML(String urlPath) {

            StringBuilder xmlResult = new StringBuilder();
            try {
                URL url = new URL(urlPath);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                int response = connection.getResponseCode();
                Log.d(TAG, "downloadXML: response code is " + response);
//    InputStream inputstream=connection.getInputStream();
//    InputStreamReader inputstreamreader=new InputStreamReader(inputstream);
//    BufferedReader reader= new BufferedReader(inputstreamreader);
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                int charread;
                char[] inputbuffer = new char[500];
                while (true) {
                    charread = reader.read(inputbuffer);
                    if (charread < 0) {
                        break;
                    }
                    if (charread > 0) {
                        xmlResult.append(String.copyValueOf(inputbuffer, 0, charread));
                    }

                }
               reader.close();


            } catch (MalformedURLException e) {
                Log.e(TAG, "downloadXML: Invalid URL" + e.getMessage());
            } catch (IOException e) {
                Log.e(TAG, "downloadXML: IO Exception"+ e.getMessage());
            }catch ( SecurityException e){
                Log.e(TAG, "downloadXML: SecurityException . Needs permission ? "+e.getMessage());
            }

            return xmlResult.toString();
        }
    }

}