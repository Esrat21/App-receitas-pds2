package com.example.foodlibrary;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class myAdapter extends RecyclerView.Adapter<myAdapter.myviewholder> {

    ArrayList<dataModel> dataholder;
    postListener Listener;

    public myAdapter(ArrayList<dataModel> dataholder,postListener Listener) {
        this.dataholder = dataholder;
        this.Listener = Listener;
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_roll_design,parent,false);
        return new myviewholder(view, Listener);
    }

    @Override
    public void onBindViewHolder(@NonNull myviewholder holder, int position) {

        try {
            holder.img.setImageBitmap(new GetImages().execute((dataholder.get(position).getImage())).get());
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        holder.header.setText(dataholder.get(position).getHeader());
        holder.desc.setText(dataholder.get(position).getDesc());
    }

    @Override
    public int getItemCount() {
        return dataholder.size();
    }

    public class myviewholder extends RecyclerView.ViewHolder implements View.OnClickListener{

        ImageView img;
        TextView header,desc;
        postListener postListener;

        public myviewholder(@NonNull View itemView, postListener postListener) {
            super(itemView);
            img = itemView.findViewById(R.id.img1);
            header = itemView.findViewById(R.id.t1);
            desc = itemView.findViewById(R.id.t2);
            this.postListener = postListener;

            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View view) {
             postListener.postClick(view ,getAdapterPosition());
        }
    }
    public interface postListener{
           void postClick(View v,int position);
    }

    private Bitmap getImageBitmap(String src) {
        try {
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

    public class GetImages extends AsyncTask<String, Integer, Bitmap> {
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
}
