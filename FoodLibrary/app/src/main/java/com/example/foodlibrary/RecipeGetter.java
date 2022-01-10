package com.example.foodlibrary;

import android.app.AlertDialog;
import android.content.Context;
import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class RecipeGetter extends AsyncTask<String,Void,String> {

    Context context;
    AlertDialog alertDialog;

    RecipeGetter (Context ctx){
        context = ctx;
    }

    @Override
    protected String doInBackground(String... params) {

        String sendUrl;

        sendUrl = "http://10.0.2.2/getAllReceitas.php";
        try {
            //REQUEST
            URL url = new URL(sendUrl);
            HttpURLConnection urlConn = (HttpURLConnection)url.openConnection();
            urlConn.setRequestMethod("POST");
            urlConn.setDoInput(true);
            urlConn.setDoOutput(true);
            OutputStream outputStream = urlConn.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, StandardCharsets.UTF_8));
            String postData = "";
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

            String[] resultado = result.split("/");

            String nomes = resultado[0];
            String descricoes = resultado[1];
            String tipos = resultado[2];

            //System.out.println(s1[0]);

            return nomes;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return "f";

    }

    @Override
    protected void onPreExecute() {
        alertDialog = new AlertDialog.Builder(context).create();
    }

    @Override
    protected void onPostExecute(String result) {

    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

}
