package com.example.foodlibrary;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.View;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class BackgroundWorker extends AsyncTask<String,Void,String> {

    private WeakReference<Context> context;
    AlertDialog alertDialog;
    String type = "";

    public BackgroundWorker (Context ctx){
        context = new WeakReference<>(ctx);
    }

    @Override
    protected String doInBackground(String... params) {

        type = params[0];


        String sendUrl;

        System.out.println(type);

        if(type == "cadastro"){
            String email = params[1];
            String pwd = params[2];

            sendUrl = "http://10.0.2.2/cadastro.php";
            try {
                //REQUEST
                URL url = new URL(sendUrl);
                HttpURLConnection urlConn = (HttpURLConnection)url.openConnection();
                urlConn.setRequestMethod("POST");
                urlConn.setDoInput(true);
                urlConn.setDoOutput(true);
                OutputStream outputStream = urlConn.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String postData = URLEncoder.encode("email","UTF-8")+"="+URLEncoder.encode(email,"UTF-8") + "&" +
                        URLEncoder.encode("senha","UTF-8")+"="+URLEncoder.encode(pwd,"UTF-8");
                bufferedWriter.write(postData);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                //RESPONSE
                InputStream inputStream = urlConn.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                String result = "";
                String line = "";

                while((line = bufferedReader.readLine())!=null){
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                urlConn.disconnect();

                return result;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


        }else if(type == "login"){
            String email = params[1];
            String pwd = params[2];
            sendUrl = "http://10.0.2.2/login.php";
            try {
                //REQUEST
                URL url = new URL(sendUrl);
                HttpURLConnection urlConn = (HttpURLConnection)url.openConnection();
                urlConn.setRequestMethod("POST");
                urlConn.setDoInput(true);
                urlConn.setDoOutput(true);
                OutputStream outputStream = urlConn.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String postData = URLEncoder.encode("email","UTF-8")+"="+URLEncoder.encode(email,"UTF-8") + "&" +
                        URLEncoder.encode("senha","UTF-8")+"="+URLEncoder.encode(pwd,"UTF-8");
                bufferedWriter.write(postData);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                //RESPONSE
                InputStream inputStream = urlConn.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                String result = "";
                String line = "";

                while((line = bufferedReader.readLine())!=null){
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                urlConn.disconnect();

                return result;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else if(type == "addReceita"){

            String ingredientes = params[1];
            String texto = params[2];
            String usuario = params[3];
            String nome = params[4];
            String tipo = params[5];
            String nacionalidade = params[6];
            String imagem = params[7];

            System.out.println(imagem);

            sendUrl = "http://10.0.2.2/cadastraReceita.php";
            try {
                //REQUEST
                URL url = new URL(sendUrl);
                HttpURLConnection urlConn = (HttpURLConnection)url.openConnection();
                urlConn.setRequestMethod("POST");
                urlConn.setDoInput(true);
                urlConn.setDoOutput(true);
                OutputStream outputStream = urlConn.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String postData = URLEncoder.encode("ingredientes","UTF-8")+"="+URLEncoder.encode(ingredientes,"UTF-8") +"&"+
                        URLEncoder.encode("texto","UTF-8")+"="+URLEncoder.encode(texto,"UTF-8") + "&" +
                        URLEncoder.encode("usuario","UTF-8")+"="+URLEncoder.encode(usuario,"UTF-8") + "&" +
                        URLEncoder.encode("nome","UTF-8")+"="+URLEncoder.encode(nome,"UTF-8") + "&" +
                        URLEncoder.encode("tipo","UTF-8")+"="+URLEncoder.encode(tipo,"UTF-8") + "&" +
                        URLEncoder.encode("imagem","UTF-8")+"="+URLEncoder.encode(imagem,"UTF-8");
                bufferedWriter.write(postData);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                //RESPONSE
                InputStream inputStream = urlConn.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                String result = "";
                String line = "";

                while((line = bufferedReader.readLine())!=null){
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                urlConn.disconnect();

                return result;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else if(type == "getReceita"){
            String id = params[1];

            sendUrl = "http://10.0.2.2/getReceita.php";
            try {
                //REQUEST
                URL url = new URL(sendUrl);
                HttpURLConnection urlConn = (HttpURLConnection)url.openConnection();
                urlConn.setRequestMethod("POST");
                urlConn.setDoInput(true);
                urlConn.setDoOutput(true);
                OutputStream outputStream = urlConn.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String postData = URLEncoder.encode("id","UTF-8")+"="+URLEncoder.encode(id,"UTF-8");
                bufferedWriter.write(postData);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();


                //RESPONSE
                InputStream inputStream = urlConn.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                String result = "";
                String line = "";

                while((line = bufferedReader.readLine())!=null){
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                urlConn.disconnect();

                return result;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    protected void onPreExecute() {
        if(type!="getReceita"){
            alertDialog = new AlertDialog.Builder(context.get()).create();
        }

    }

    @Override
    protected void onPostExecute(String result) {
        if(type == "cadastro"){
            String data;
            if (result != "false"){
                data = "true";
            }else{
                data = "false";
            }
           TelaCadastro.acessaLogin(this.context,data);
        }else if(type == "login"){
            boolean val;
            if (result != "false"){
                String[] data = result.split("<>");
                TelaLogin.goNext(this.context,data);
            }else{

            }
        }else if(type == "addReceita"){
            alertDialog.setTitle("Cadastro Receita");
        }
        alertDialog.setMessage(result);
        alertDialog.show();
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

}
