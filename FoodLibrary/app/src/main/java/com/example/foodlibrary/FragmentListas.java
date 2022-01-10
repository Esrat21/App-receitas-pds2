package com.example.foodlibrary;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import java.util.ArrayList;

public class FragmentListas extends Fragment {

    RecyclerView recyclerView;
    private myAdapter.postListener listener;
    ArrayList<dataModel> dataholder;

    String[] names;
    String[] ids;
    String[] imagens;
    String[] ingredientes;
    String[] modoPreparo;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_listas, container, false);

        recyclerView = view.findViewById(R.id.recview2);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        dataholder = new ArrayList<>();

        Bundle bundle = this.getArguments();

        System.out.println(bundle);

        if (bundle == null) {
            dataholder = new ArrayList<>();
            FragmentListas.RecipeGetter rs = new FragmentListas.RecipeGetter(view.getContext());
            rs.execute("1");
        }else{
            dataholder = new ArrayList<>();
            FragmentListas.BuscaReceita br = new FragmentListas.BuscaReceita(view.getContext());
            br.execute(bundle.getString("busca"));
        }

        return view;
    }

    public class RecipeGetter extends AsyncTask<String,Void,String> {

        private WeakReference<Context> context;
        AlertDialog alertDialog;

        public RecipeGetter (Context ctx){
            context = new WeakReference<>(ctx);
        }


        @Override
        protected String doInBackground(String... params) {

            String sendUrl;

            String id = params[0];

            sendUrl = "http://10.0.2.2/getFavoritas.php";
            String[] resultado = new String[0];
            try {
                //REQUEST
                URL url = new URL(sendUrl);
                HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
                urlConn.setRequestMethod("POST");
                urlConn.setDoInput(true);
                urlConn.setDoOutput(true);
                OutputStream outputStream = urlConn.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, StandardCharsets.UTF_8));
                String postData = URLEncoder.encode("id", "UTF-8") + "=" + URLEncoder.encode(id, "UTF-8");
                bufferedWriter.write(postData);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                //RESPONSE
                InputStream inputStream = urlConn.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.ISO_8859_1));
                String result = "";
                String line;

                while ((line = bufferedReader.readLine()) != null) {
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                urlConn.disconnect();

                resultado = result.split("<>");

                try {
                    resultado[0] = resultado[0].replace("[", "").replace("]", "").replace("\"", "");
                    names = resultado[0].split(",");

                    String descricoes = resultado[1];
                    String tipos = resultado[2];

                    resultado[3] = resultado[3].replace("[", "").replace("]", "").replace("\"", "");

                    resultado[4] = resultado[4].replace("[", "").replace("]", "").replace("\"", "");

                    imagens = resultado[4].split(",");

                    resultado[5] = resultado[5].replace("[", "").replace("]", "").replace("\"", "");
                    ingredientes = resultado[5].split(",");

                    resultado[1] = resultado[1].replace("[", "").replace("]", "").replace("\"", "");
                    modoPreparo = resultado[1].split(",");

                    ids = resultado[3].split(",");
                    for (String img : imagens) {
                        System.out.println(img.replace("/", ""));
                    }

                    for (int i = 0; i < names.length; i++) {

                        setOnClickListener();
                        System.out.println("no for");
                        dataModel ob1 = new dataModel("http://10.0.2.2" + imagens[i].replace("\\", ""), names[i], "desc");
                        dataholder.add(ob1);
                        recyclerView.setAdapter(new myAdapter(dataholder, listener));
                    }

                    return resultado[0];
                } catch (Exception e) {

                }
                return result;
            } catch (IOException e) {
                e.printStackTrace();
            }

            return "catch";

        }

        @Override
        protected void onPreExecute() {
            alertDialog = new AlertDialog.Builder(context.get()).create();
        }

        @Override
        protected void onPostExecute(String result) {
            alertDialog.setMessage(result);
            alertDialog.show();
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        private Bitmap imageDecode(String image){
            byte[] decodedString = Base64.decode(image, Base64.DEFAULT);
            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

            return decodedByte;
        }

    }

    public class BuscaReceita extends AsyncTask<String,Void,String> {

        private WeakReference<Context> context;
        AlertDialog alertDialog;

        public BuscaReceita (Context ctx){
            context = new WeakReference<>(ctx);
        }


        @Override
        protected String doInBackground(String... params) {

            String sendUrl;

            sendUrl = "http://10.0.2.2/buscaReceitas.php";
            try {

                String nome = params[0];

                //REQUEST
                URL url = new URL(sendUrl);
                HttpURLConnection urlConn = (HttpURLConnection)url.openConnection();
                urlConn.setRequestMethod("POST");
                urlConn.setDoInput(true);
                urlConn.setDoOutput(true);
                OutputStream outputStream = urlConn.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, StandardCharsets.UTF_8));
                String postData = URLEncoder.encode("nome","UTF-8")+"="+URLEncoder.encode(nome,"UTF-8");
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

                String[] resultado = result.split("<>");

                try {
                    resultado[0] = resultado[0].replace("[","").replace("]","").replace("\"","");
                    names = resultado[0].split(",");

                    String descricoes = resultado[1];
                    String tipos = resultado[2];

                    resultado[3] = resultado[3].replace("[","").replace("]","").replace("\"","");

                    resultado[4] = resultado[4].replace("[","").replace("]","").replace("\"","");

                    imagens = resultado[4].split(",");

                    resultado[5] = resultado[5].replace("[","").replace("]","").replace("\"","");
                    ingredientes = resultado[5].split(",");

                    resultado[1] = resultado[1].replace("[","").replace("]","").replace("\"","");
                    modoPreparo = resultado[1].split(",");

                    ids = resultado[3].split(",");
                    for (String img : imagens) {
                        System.out.println(img.replace("/",""));
                    }

                    for (int i=0;i<names.length;i++) {

                        setOnClickListener();
                        System.out.println();
                        dataModel ob1 = new dataModel("http://10.0.2.2" + imagens[i].replace("\\",""),names[i],"desc");
                        dataholder.add(ob1);
                        recyclerView.setAdapter(new myAdapter(dataholder, listener));
                    }

                    return resultado[0];
                }catch (Exception e){

                }

            } catch (IOException e) {
                e.printStackTrace();
            }

            return "f";

        }

        @Override
        protected void onPreExecute() {
            alertDialog = new AlertDialog.Builder(context.get()).create();
        }

        @Override
        protected void onPostExecute(String result) {

        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        private Bitmap imageDecode(String image){
            byte[] decodedString = Base64.decode(image, Base64.DEFAULT);
            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

            return decodedByte;
        }

    }

    private void setOnClickListener() {
        listener = new myAdapter.postListener() {
            @Override
            public void postClick(View v, int position) {
                System.out.println(position);
                Intent intent = new Intent(getContext(), TelaReceita.class);

                intent.putExtra("nome", names[position]);
                intent.putExtra("imagem", dataholder.get(position).getImage());
                intent.putExtra("ingredientes", ingredientes[position]);
                intent.putExtra("modoPreparo", modoPreparo[position]);

                startActivity(intent);
            }
        };
    }

}

