package com.example.foodlibrary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class TelaPrincipal extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, PopupMenu.OnMenuItemClickListener {
    private DrawerLayout drawer;
    private String filtro = "nome";
    Bundle bundle = new Bundle();
    EditText txtPesquisa ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_principal);
        txtPesquisa = findViewById(R.id.pesquisa);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);


        String pesquisa = txtPesquisa.getText().toString();

        txtPesquisa.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // If the event is a key-down event on the "enter" button
                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    // Perform action on key press
                    Toast.makeText(v.getContext(), "Buscando por: " + txtPesquisa.getText().toString(), Toast.LENGTH_SHORT).show();
                    System.out.println(txtPesquisa.getText().toString());

                    bundle.putString("filtro", txtPesquisa.getText().toString());
                    bundle.putString("tipo", filtro);
                    FragmentHome fragmentHome = new FragmentHome();

                    fragmentHome.setArguments(bundle);

                    getSupportFragmentManager().beginTransaction().replace(R.id.frag_container,fragmentHome).commit();

                    return true;
                }
                return false;
            }
        });

        drawer = findViewById(R.id.tela_principal);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toogle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toogle);
        toogle.syncState();

        if (savedInstanceState == null) {

            getSupportFragmentManager().beginTransaction().replace(R.id.frag_container,
                    new FragmentHome()).commit();
            navigationView.setCheckedItem(R.id.menu_home);
        }

        contextOfApplication = getApplicationContext();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_receita:
                 getSupportFragmentManager().beginTransaction().replace(R.id.frag_container,
                         new FragmentCadastroReceita()).commit();
                break;
            case R.id.menu_perfil:
                getSupportFragmentManager().beginTransaction().replace(R.id.frag_container,
                        new FragmentPerfil()).commit();
                break;
            case R.id.menu_listas:
                getSupportFragmentManager().beginTransaction().replace(R.id.frag_container,
                        new FragmentListas()).commit();
                break;
            case R.id.menu_preferencias:
                getSupportFragmentManager().beginTransaction().replace(R.id.frag_container,
                        new FragmentPreferencias()).commit();
                break;
            case R.id.menu_home:
                getSupportFragmentManager().beginTransaction().replace(R.id.frag_container,
                        new FragmentHome()).commit();
                break;
            case R.id.menu_assinatura:
                Toast.makeText(this,"Assinatura ainda nao disponivel", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_logout:
                boolean isFileCreated = StorageController.create(getApplicationContext(), "storage.json", "{}");
                if(isFileCreated) {
                    //proceed with storing the first todo  or show ui
                    Intent intent = new Intent(getApplicationContext(), TelaLogin.class);
                    startActivity(intent);

                } else {
                    //show error or try again.
                    Toast.makeText(this,"Falha ao sair, tente novamente", Toast.LENGTH_SHORT).show();
                }
                break;
        }
        drawer.closeDrawer(GravityCompat.START);

        return true;
    }


    @Override
    public void onBackPressed(){
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }else {
            super.onBackPressed();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1) {
            //TODO: action
        }
    }

    public void showPopup(View v){
        PopupMenu popup = new PopupMenu(this ,v);
        popup.setOnMenuItemClickListener(this);
        popup.inflate(R.menu.popup_menu);
        popup.show();

    }

    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.filtronome:
                    filtro = "nome";

                return true;
            case R.id.filtroingrediente:
                    filtro = "ingrediente";

                return true;
            case R.id.filtronacionalidade:
                    filtro = "nacionalidade";

                return true;
            case R.id.filtrotipo:
                    filtro = "tipo";

                return true;
            default:
                return false;
        }
    }

    public static Context contextOfApplication;
    public static Context getContextOfApplication()
    {
        return contextOfApplication;
    }



}
