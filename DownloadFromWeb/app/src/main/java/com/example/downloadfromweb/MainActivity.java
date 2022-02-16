package com.example.downloadfromweb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.util.Log;
import android.view.View;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.Buffer;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {
    private String mailru="https://www.google.ru/webhp?client=opera&sourceid=opera";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*DownloadTask task=new DownloadTask();
        try {
           String result= task.execute(mailru).get();
           Log.i("URL",result);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
    }

    public void GoToNewActive(View view) {
        Intent intent=new Intent(this,DownloadImage.class);
        startActivity(intent);
    }

    public void GotoStar(View view) {
        Intent intent=new Intent(this,TryGuess.class);
        startActivity(intent);
    }

    private static class DownloadTask extends AsyncTask<String,Void,String>{

        @Override
        protected String doInBackground(String... strings) {
            StringBuilder result= new StringBuilder();
            URL url=null;
            HttpURLConnection urlConnection=null;

            try {
                 url=new URL(strings[0]);//получаем ссылку
                urlConnection=(HttpURLConnection)url.openConnection();// Открываем через браузер(Тип совместимый)
                //Для получения доступа нужно изменить андроид манифест для promissions
                InputStream in =urlConnection.getInputStream();//Поток ввода для чтения из соединенния
                InputStreamReader reader=new InputStreamReader(in);//Чтение из потока
                BufferedReader bufferedReader=new BufferedReader(reader);//Чтение не по символам, а по строкам
                String line=bufferedReader.readLine();//Чтение строки
                while (line!=null){
                    result.append(line);//Добавляем в строку
                    line=bufferedReader.readLine();
                }
                return result.toString();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            finally {
                if(urlConnection!=null){
                    urlConnection.disconnect();
                }
            }

            return result.toString();
        }
    }
}
