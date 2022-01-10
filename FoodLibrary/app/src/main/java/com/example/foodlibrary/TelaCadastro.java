package com.example.foodlibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.lang.ref.WeakReference;
import java.util.regex.Pattern;

public class TelaCadastro extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_cadastro);
    }

    public void cadastraUsuario(View view){
        String email = ((EditText)findViewById(R.id.username)).getText().toString();
        String emailConfirma = ((EditText)findViewById(R.id.confirmausername)).getText().toString();

        String senha = ((EditText)findViewById(R.id.senha)).getText().toString();
        String senhaConfirma = ((EditText)findViewById(R.id.confirmasenha)).getText().toString();

        String type = "cadastro";

        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(getApplicationContext(),email + " " + senha,duration);
        toast.show();

        if(email.equals(emailConfirma) && senha.equals(senhaConfirma) && isValidPassword(senha) && isValidEmailId(email)) {
            BackgroundWorker bgWrk = new BackgroundWorker(this);
            bgWrk.execute(type, email, senha);

        }
    }

    public void acessaLogin(View v){
        Intent intent = new Intent(v.getContext(), TelaLogin.class);
        startActivity(intent);
    }

    public static void acessaLogin(WeakReference<Context> ctx,String data){

        Toast toast = Toast.makeText(ctx.get(),"Usuario cadastrado com sucesso",Toast.LENGTH_LONG);
        toast.show();

        Intent intent = new Intent(ctx.get(), TelaLogin.class);
        ctx.get().startActivity(intent);
    }

    public static boolean isValidPassword(String s) {
        Pattern PASSWORD_PATTERN;
        PASSWORD_PATTERN = Pattern.compile(
                "[a-zA-Z0-9\\!\\@\\#\\$]{8,24}");

        return !TextUtils.isEmpty(s) && PASSWORD_PATTERN.matcher(s).matches();
    }

    private boolean isValidEmailId(String email){

        return Pattern.compile("^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$").matcher(email).matches();
    }
}