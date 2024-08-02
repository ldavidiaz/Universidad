package es.umh.dadm.mispeliculas; 

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import es.umh.dadm.mispeliculas; 
import es.umh.dadm.mispeliculas; 
import es.umh.dadm.mispeliculas; 
import es.umh.dadm.mispeliculas; 
import es.umh.dadm.mispeliculas; 

public class PlataformasActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton add_button;

    MyDatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.plataforma_activty);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        add_button = findViewById(R.id.add_button);

        dbHelper = new MyDatabaseHelper(PlataformasActivity.this);

        List<Plataforma> plataformas = new ArrayList<>();
        plataformas = dbHelper.listaPlataformas(UsuarioLogeado.obtenerInstancia().getId());

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setNestedScrollingEnabled(false);

        recyclerView.setAdapter(new PlatafromAdaptador(PlataformasActivity.this, plataformas));

        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PlataformasActivity.this, AddPlataformaActivity.class));
                finish();
            }
        });



    }
}
