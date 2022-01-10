package com.example.foodlibrary;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Base64;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.jackandphantom.androidlikebutton.AndroidLikeButton;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutionException;

public class TelaReceita extends AppCompatActivity {

    AndroidLikeButton likeButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_receita);

        TextView header = findViewById(R.id.nometextview);

        ImageView imagem = findViewById(R.id.imagemReceita);
        TextView nome = findViewById(R.id.nomeReceita);
        TextView ingredientes = findViewById(R.id.ingredientesReceita);
        TextView modoPreparo = findViewById(R.id.modoPreparoReceita);

        likeButton = findViewById(R.id.like);

        likeButton.setOnLikeEventListener(new AndroidLikeButton.OnLikeEventListener() {
            @Override
            public void onLikeClicked(AndroidLikeButton androidLikeButton) {
                likeController lk = new likeController();
                lk.execute("0");
                System.out.println("favoritou");
            }

            @Override
            public void onUnlikeClicked(AndroidLikeButton androidLikeButton) {
                likeController lk = new likeController();
                lk.execute("1");
                System.out.println("desfavoritou");
            }
        });

        Bundle extras = getIntent().getExtras();

        if (extras!= null){
            imagem.setImageURI(Uri.parse(extras.getString("imagem")));

            try {
                imagem.setImageBitmap(new GetImages().execute(extras.getString("imagem")).get());

            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            nome.setText(extras.getString("nome"));

            System.out.println(nome);

            header.setText("Receita");

            ingredientes.setText(extras.getString("ingredientes").replace("-",", "));
            modoPreparo.setText(extras.getString("modoPreparo"));
        }
    }

    private class GetImages extends AsyncTask<String, Integer, Bitmap> {
        protected Bitmap doInBackground(String... params) {
            try {

                String src = params[0];

                URL url = new URL(src);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setDoInput(true);
                connection.connect();
                InputStream input = connection.getInputStream();
                Bitmap myBitmap = BitmapFactory.decodeStream(input);
                return myBitmap;
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }

        protected void onProgressUpdate(Integer... progress) {

        }

        protected void onPostExecute(Long result) {

        }
    }

    private class likeController extends AsyncTask<String,Void,String> {

        private WeakReference<Context> context;
        AlertDialog alertDialog;

        @Override
        protected String doInBackground(String... params) {

            String sendUrl;
            String tipo = params[0];
            String idUsuario = "1";

            if(tipo == "0"){
                sendUrl = "http://10.0.2.2/favoritarReceita.php";
                try {
                    //REQUEST
                    URL url = new URL(sendUrl);
                    HttpURLConnection urlConn = (HttpURLConnection)url.openConnection();
                    urlConn.setRequestMethod("POST");
                    urlConn.setDoInput(true);
                    urlConn.setDoOutput(true);
                    OutputStream outputStream = urlConn.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, StandardCharsets.UTF_8));
                    String postData = URLEncoder.encode("id","UTF-8")+"="+URLEncoder.encode(idUsuario,"UTF-8");
                    bufferedWriter.write(postData);
                    bufferedWriter.flush();
                    bufferedWriter.close();
                    outputStream.close();

                    //RESPONSE
                    InputStream inputStream = urlConn.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.ISO_8859_1));
                    String result = "";
                    String line;

                    while((line = bufferedReader.readLine())!=null){
                        result += line;
                    }
                    bufferedReader.close();
                    inputStream.close();
                    urlConn.disconnect();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }else if(tipo == "1"){
                sendUrl = "http://10.0.2.2/desfavoritarReceita.php";
                try {
                    //REQUEST
                    URL url = new URL(sendUrl);
                    HttpURLConnection urlConn = (HttpURLConnection)url.openConnection();
                    urlConn.setRequestMethod("POST");
                    urlConn.setDoInput(true);
                    urlConn.setDoOutput(true);
                    OutputStream outputStream = urlConn.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, StandardCharsets.UTF_8));
                    String postData = URLEncoder.encode("id","UTF-8")+"="+URLEncoder.encode(idUsuario,"UTF-8");
                    bufferedWriter.write(postData);
                    bufferedWriter.flush();
                    bufferedWriter.close();
                    outputStream.close();

                    //RESPONSE
                    InputStream inputStream = urlConn.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.ISO_8859_1));
                    String result = "";
                    String line;

                    while((line = bufferedReader.readLine())!=null){
                        result += line;
                    }
                    bufferedReader.close();
                    inputStream.close();
                    urlConn.disconnect();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            return "f";

        }

        @Override
        protected void onPreExecute() {

        }

        @Override
        protected void onPostExecute(String result) {

        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }
    }
}
