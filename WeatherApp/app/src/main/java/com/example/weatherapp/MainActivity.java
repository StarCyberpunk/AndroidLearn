package com.example.weatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {
private String jsonSite="https://api.openweathermap.org/data/2.5/weather?q=Kazan&appid={}";
    private String jsonSite2="https://api.openweathermap.org/data/2.5/weather?q=Moscow&appid={}";
    private String jsonSite3="https://api.openweathermap.org/data/2.5/weather?q=London&appid={}";
    private Button b1;
    private Button b2;
    private Button b3;
    private String choise="https://api.openweathermap.org/data/2.5/weather?q=Kazan&appid={}";
    private TextView textView;
    //https://openweathermap.org/current
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView=findViewById(R.id.textViewte);
        b1=findViewById(R.id.button);
        b2=findViewById(R.id.button2);
        b3=findViewById(R.id.button3);




    }
    private void TakeData(String se){
        try {
            DownloadJsonTask task=new DownloadJsonTask();
            String s =task.execute(se).get();
            JSONObject jsonObject=new JSONObject(s);
            String name=jsonObject.getString("name");
            JSONObject main=jsonObject.getJSONObject("main");
            Integer temp=main.getInt("temp");
            Integer tempfeels=main.getInt("feels_like");
            temp=temp-273;
            tempfeels=tempfeels-273;

            textView.setText("Name:"+name+"\n"+"Temp:"+temp+"\n"+"Temp like:"+tempfeels+"\n");
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void DoIt(View view) {
        if(view.equals(b1)){
            choise=jsonSite;
            TakeData(choise);
        }
        if(view.equals(b2)){
           choise=jsonSite3;
            TakeData(choise);
        }
        if(view.equals(b3)){
            choise=jsonSite2;
            TakeData(choise);
        }
    }

    private static class DownloadJsonTask extends AsyncTask<String,Void,String> {


        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.i("Res",s);
            try {
                JSONObject jsonObject=new JSONObject(s);
                String name=jsonObject.getString("name");
                JSONObject main=jsonObject.getJSONObject("main");
                Double temp=main.getDouble("temp");
                Double tempfeels=main.getDouble("feels_like");
                JSONObject weather=jsonObject.getJSONObject("weather");
                String o=weather.getString("main");
                Log.i("Res",name+" "+ temp);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

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

            return null;
        }
    }
}