package com.example.nick.androidthread;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick1(View view) {
        new LoadImageTask().execute("https://i.ytimg.com/vi/o2TaybKpwFo/maxresdefault.jpg");
    }
    public class LoadImageTask extends AsyncTask<String,Integer,Bitmap>{
        @Override
        protected Bitmap doInBackground(String... urls){

            try {
                URL imgURL = new URL(urls[0]);
                InputStream imgStream = imgURL.openConnection().getInputStream();
                Bitmap bmp = BitmapFactory.decodeStream(imgStream);
                return bmp;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
        protected void onProgressUpdate(Integer... values) {
            textView = findViewById(R.id.text_prc1);
            textView.setText("Processing...");
        }

        protected void onPostExecute(Bitmap bmp)  {
            imageView = findViewById(R.id.img_id1);
            imageView.setImageBitmap(bmp);
        }
    }

    public void onClick2(View view) {
//        new LoadImageTask().execute("https://lh3.googleusercontent.com/lL2rRUrk0xR0bVRXvkLBcqT-a3qe-uWir6U_Ze1WeLb3D1pPiY1RXRUAO2DUmx8VBWo=h900");
        new  Thread(new Runnable() {
            @Override
            public void run() {
                URL url = null;
                try {
                    url = new URL("https://lh3.googleusercontent.com/lL2rRUrk0xR0bVRXvkLBcqT-a3qe-uWir6U_Ze1WeLb3D1pPiY1RXRUAO2DUmx8VBWo=h900");
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                Bitmap bmp = null;
                try {
                    bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                final ImageView img =findViewById(R.id.img_id1);
                final Bitmap b = bmp;

                img.post(new Runnable() {
                    @Override
                    public void run() {
                        img.setImageBitmap(b);
                    }
                });

            }
        });
    }
}
