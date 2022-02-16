package com.example.downloadfromweb;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.PatternMatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TryGuess extends AppCompatActivity {
    private String site="https://www.rbc.ru/photoreport/28/07/2021/61010af99a7947fe58e4d965";
    private ImageView imageView;
    private Button b1;
    private Button b2;
    private Button b3;
    private Button b4;
    private String[] Names=new String[10];
   private String[] Images=new String[10];
   private ArrayList<Button> buttons=new ArrayList<>();
   private String ChoiseImage="";
   private int Win;
   private int Winbut;
   private Random rnd=new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_try_guess);

        imageView=findViewById(R.id.imageViewGuess);
        b1=findViewById(R.id.buttonFirst);
        b2=findViewById(R.id.buttonSecond);
        b3=findViewById(R.id.buttonThird);
        b4=findViewById(R.id.buttonFourth);

        buttons.add(b1);
        buttons.add(b2);
        buttons.add(b3);
        buttons.add(b4);
        /*String name="wdawd,awdad,wdada,adwad";
        String[] names=name.split(",");
        for(String nae:names){
            Log.i("Name",nae);
        }

        String geometry="Geomery";
        String a=geometry.substring(2,5);
        Log.i("ome",a );

        String river="MississippiMissippi";
        Pattern pattern=Pattern.compile("Mi(.*?)pi");
        //ssissip
        // ssip
        Matcher matcher=pattern.matcher(river);
        while (matcher.find()){
            Log.i("Names",matcher.group(1));
        }*/

        DownloadTask task=new DownloadTask();
        try {
            ArrayList<String[]> result= task.execute(site).get();
            for (String[] res:result) {
                for (int i=0;i<res.length;i++){
                    Log.i("F",res[i]);
                }
            }
            for (int i=0;i<result.get(0).length;i++){
                Names[i]=result.get(0)[i];
            }
            for (int i=0;i<result.get(1).length;i++){
                Images[i]=result.get(1)[i];
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }




        StartGame(rnd);
    }
    public void StartGame(Random rnd){
        Win=rnd.nextInt(9);

        for (Button but:buttons
        ) {
            int tr=rnd.nextInt(9);
            while (tr==Win){
                tr=rnd.nextInt(9);
            }
            //TOOD Не повторяющиеся
            but.setText(Names[tr]);
        }
        Winbut=rnd.nextInt(3);
        buttons.get(Winbut).setText(Names[Win]);
        ChoiseImage=Images[Win];
        downloadImage2();
    }

    public void Answer(View view) {
        if(view.equals(buttons.get(Winbut))){
            Log.i("Answer","Right");
            StartGame(rnd);
        }else {
            Log.i("Answer","Wrong");
        }
    }

    private static class DownloadTask extends AsyncTask<String,Void,ArrayList<String[]>> {

        @Override
        protected ArrayList<String[]> doInBackground(String... strings) {

            ArrayList<String[]> result=new ArrayList<>();
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
                Pattern pattern=Pattern.compile("<div class=\"gallery_vertical__title\">(.*?)</div>");
                Pattern pattern2=Pattern.compile("https://s0.rbk.ru/v6_top_pics/resized/(.*?).jpg");
                int kol=0;
                int zol=0;
                String[] namess=new String[10];
                String[] imgkol=new String[10];

                while (line!=null){
                    Matcher matcher=pattern.matcher(line);
                    Matcher matcher2=pattern2.matcher(line);
                    if (matcher.find()){
                        String a=matcher.group(1).toString();
                        String[] names=a.split(" ");
                        Log.i("Names",names[2]+ " "+names[3]);
                        namess[kol]= names[2]+ " "+names[3]+" ";

                        kol++;
                    }
                    if(matcher2.find()){
                        imgkol[zol]="https://s0.rbk.ru/v6_top_pics/resized/"+matcher2.group(1)+".jpg";
                        Log.i("Names2",matcher2.group(1));
                        zol++;
                    }
                    //Добавляем в строку
                    line=bufferedReader.readLine();
                }
                result.add(namess);
                result.add(imgkol);



                return result;
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

            return result;
}
    }
    public void downloadImage2() {
        DownloadImageTask imageTask=new DownloadImageTask();
        Bitmap bitmap=null;
        try {
            bitmap=imageTask.execute(ChoiseImage).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        imageView.setImageBitmap(bitmap);
    }



    private class DownloadImageTask extends AsyncTask<String,Void, Bitmap>{

        @Override
        protected Bitmap doInBackground(String... strings) {
            URL url=null;
            HttpURLConnection urlConnection=null;
            try {
                url=new URL(ChoiseImage);
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
