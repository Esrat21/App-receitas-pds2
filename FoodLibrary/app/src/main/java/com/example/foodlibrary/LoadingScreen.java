package com.example.foodlibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import java.lang.ref.WeakReference;

public class LoadingScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading_screen);

        WeakReference<Context> cReference = new WeakReference<Context>(getBaseContext());

        boolean isFilePresent = StorageController.isFilePresent(cReference.get(), "storage.json");
        if(isFilePresent) {
            String jsonString = StorageController.read(cReference.get(), "storage.json");
            //do the json parsing here and do the rest of functionality of app

            System.out.println(jsonString);

            if (jsonString == null){
                Intent intent = new Intent(this, TelaLogin.class);
                startActivity(intent);
            }else{
                Intent intent = new Intent(this, TelaPrincipal.class);
                startActivity(intent);
            }

        } else {
            boolean isFileCreated = StorageController.create(cReference.get(), "storage.json", "{}");
            if(isFileCreated) {
                //proceed with storing the first todo  or show ui

                Intent intent = new Intent(this, TelaLogin.class);
                startActivity(intent);

            } else {
                //show error or try again.
                Intent intent = new Intent(this, LoadingScreen.class);
                startActivity(intent);
            }
        }
    }
}