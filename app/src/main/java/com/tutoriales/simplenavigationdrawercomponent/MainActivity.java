package com.tutoriales.simplenavigationdrawercomponent;

import android.os.Bundle;
import android.view.View;
import android.view.Menu;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.tutoriales.simplenavigationdrawercomponent.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    //Configuracion del appbar
    private AppBarConfiguration mAppBarConfiguration;
    //enlaze a la vista con view binding
    private ActivityMainBinding binding;
    //Control de navegacion
    private NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Con ViewBinding inflo la vista autogenerada y la seteo a la activity con SetContentView
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        //GetRoot te da el XML padre o raiz
        setContentView(binding.getRoot());

        //Asigno el appbar/toolbar para reemplazar el por defecto, no olvidar poner NoActionBar en el tema
        setSupportActionBar(binding.appBarMain.toolbar);

        //Asigno el set on click al boton flotante
        binding.appBarMain.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        //Obtengo el Navigation View y Drawer Layout del Activity Main (xml)
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;

        //Configura la appbar
        mAppBarConfiguration = new AppBarConfiguration
                                    .Builder(R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow) // se añade los fragments a utilizarse
                                    .setOpenableLayout(drawer) //Se enlaza el drawer layout para mostrar el icono de hamburguesa
                                    .build();

        //Obtiene el navcontroller del content_main.xml
        navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        //Enlaza el toolbar/appbar con el navcontroller
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        //Enlaza el navcontroller con el navigationview para navegar
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Infla el menu del app bar/toolbar
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        //Delega la responsabilidad de navegacion del appbar al navcontroller
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}