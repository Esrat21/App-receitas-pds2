package com.example.foodlibrary;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Arrays;

import javax.xml.transform.Result;

public class FragmentCadastroReceita extends Fragment {

    final int CODE_GALLERY_REQUEST = 999;
    private ImageView imgUpload;
    private String urlUpload;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cadastrareceita, container, false);

        EditText ing = ((EditText)view.findViewById(R.id.ingredientesReceita));
        EditText txt = ((EditText)view.findViewById(R.id.modoPreparoReceita));
        EditText name = ((EditText)view.findViewById(R.id.nomeReceita));
        EditText type = ((EditText)view.findViewById(R.id.tipoReceita));
        EditText nac = ((EditText)view.findViewById(R.id.nacionalidadeReceita));

        Button button = (Button) view.findViewById(R.id.btnCadastrarReceita);

        Button btnImagem = (Button) view.findViewById(R.id.btnImagem);
        imgUpload = (ImageView) view.findViewById(R.id.imagem);

        btnImagem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        CODE_GALLERY_REQUEST);
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                imgUpload.invalidate();
                BitmapDrawable drawable = (BitmapDrawable) imgUpload.getDrawable();
                Bitmap bitmap = drawable.getBitmap();

                try {
                    String ingredientes = ing.getText().toString();
                    String texto = txt.getText().toString();
                    String usuario = getActiveUser(v);
                    String nome = name.getText().toString();
                    String tipo = type.getText().toString();
                    String nacionalidade = nac.getText().toString();

                    String t = "addReceita";
                    BackgroundWorker bgWrk = new BackgroundWorker(v.getContext());
                    bgWrk.execute(t,ingredientes,texto,usuario,nome,tipo,nacionalidade,imgToString(bitmap));

                }catch (NullPointerException e){
                    System.out.println("Deu Null pointer");
                }

            }
        });

        return view;
    }

    public String getActiveUser(View v){
        String usr = null;
        boolean isFilePresent = StorageController.isFilePresent(v.getContext(), "storage.json");
        if(isFilePresent) {
            String jsonString = StorageController.read(v.getContext(), "storage.json");
            //do the json parsing here and do the rest of functionality of app

            System.out.println(jsonString);



            if (jsonString == null){

            }else{
                String [] data = jsonString.split("<>");
                usr = data[0];
            }

        } else {

        }

        return usr;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        System.out.println(requestCode);
        System.out.println(CODE_GALLERY_REQUEST);

        if (requestCode == CODE_GALLERY_REQUEST){
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(Intent.createChooser(intent,"selecione a imagem"),CODE_GALLERY_REQUEST);
            }else{
                Toast.makeText(getContext(),"Voce não tem permissoes",Toast.LENGTH_SHORT).show();
            }
            return;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if(requestCode == CODE_GALLERY_REQUEST && resultCode == Activity.RESULT_OK && data!=null){

            Uri filepath = data.getData();

            Context applicationContext = TelaPrincipal.getContextOfApplication();

            try {
                InputStream inputStream = applicationContext.getContentResolver().openInputStream(filepath);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                imgUpload.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    public String imgToString(Bitmap bitmap){

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,outputStream);
        byte [] imageBytes = outputStream.toByteArray();

        String encodedImage = Base64.encodeToString(imageBytes,Base64.DEFAULT);

        System.out.println(encodedImage);

        return encodedImage;
    }

}
