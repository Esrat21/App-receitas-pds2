package com.example.foodlibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.lang.ref.WeakReference;

public class TelaLogin extends AppCompatActivity {

    String email;

    public static void goNext(WeakReference<Context> context,String[] data) {
        boolean isFilePresent = StorageController.isFilePresent(context.get(), "storage.json");
        if(isFilePresent) {
            boolean isFileCreated = StorageController.create(context.get(), "storage.json", "{\"nome\":\""+data[1]+"\",\"id\":\""+data[0]+"\"}");
            if(isFileCreated) {
                //proceed with storing the first todo  or show ui
                Intent intent = new Intent(context.get(), TelaPrincipal.class);
                context.get().startActivity(intent);

            } else {
                //show error or try again.
                Intent intent = new Intent(context.get(), TelaLogin.class);
                context.get().startActivity(intent);
            }

        } else {
            boolean isFileCreated = StorageController.create(context.get(), "storage.json", "{nome:"+data[1]+",id"+data[0]+"}");
            if(isFileCreated) {
                //proceed with storing the first todo  or show ui
                Intent intent = new Intent(context.get(), TelaPrincipal.class);
                context.get().startActivity(intent);

            } else {
                //show error or try again.
                Intent intent = new Intent(context.get(), TelaLogin.class);
                context.get().startActivity(intent);
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_login);
    }

    public void acessaCadastro(View v){
        Intent intent = new Intent(v.getContext(), TelaCadastro.class);
        startActivity(intent);
    }

    public void efetuaLogin(View view){
        String email = ((EditText)findViewById(R.id.username)).getText().toString();
        String senha = ((EditText)findViewById(R.id.senha)).getText().toString();

        String type = "login";
        BackgroundWorker bgWrk = new BackgroundWorker(this);
        bgWrk.execute(type,email,senha);
    }
}