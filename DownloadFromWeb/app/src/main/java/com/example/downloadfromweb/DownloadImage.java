package com.example.downloadfromweb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.ExecutionException;

public class DownloadImage extends AppCompatActivity {
    private ImageView imageView;
    private String urlImg="https://resheto.net/images/mater/kartinka_motivatsiya_tsitata_8.jpg";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download_image);
        imageView=findViewById(R.id.imageViewImage);

    }

    public void downloadImage(View view) {
        DownloadImageTask imageTask=new DownloadImageTask();
        Bitmap bitmap=null;
        try {
            bitmap=imageTask.execute(urlImg).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        imageView.setImageBitmap(bitmap);
    }

    public void GoToThirdAct(View view) {
        Intent intent=new Intent(this,TryGuess.class);
        startActivity(intent);
    }

    private class DownloadImageTask extends AsyncTask<String,Void, Bitmap>{

        @Override
        protected Bitmap doInBackground(String... strings) {
            URL url=null;
            HttpURLConnection urlConnection=null;
            try {
                url=new URL(urlImg);
                urlConnection= (HttpURLConnection) url.openConnection();
                InputStream in =urlConnection.getInputStream();
                Bitmap bitmap= BitmapFactory.decodeStream(in);//преобразование строки с ссылкой в битмап
                return bitmap;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}