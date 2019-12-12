package cl.inacap.calendariomedico;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuAdapter;


import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cl.inacap.calendariomedico.modelo.CalendarioBaseHelper;
import cl.inacap.calendariomedico.modelo.ListaMedicamento;
import cl.inacap.calendariomedico.modelo.Medicamento;

public class AlarmaActivity extends AppCompatActivity {
    private ListView lvItems;
    private ListaMedicamentos adaptador;
    private ListView lista;
    CalendarioBaseHelper myDb;

    Button boton2;
    Button ingresarBoton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarma);

        myDb = new CalendarioBaseHelper(this);
        try {
            int Estado=1;
            Cursor data =myDb.getAllMedicamentos(Estado);
            ArrayList<Medicamento> getdata=new ArrayList<>();
            data.moveToFirst();
            int estadoInt;
            boolean estado = false;
            do {
                estadoInt = data.getInt(4);
                if (estadoInt == 1) estado = true;
                else estado = false;

                getdata.add(new Medicamento(data.getInt(0),
                        data.getString(1),
                        data.getString(2),
                        data.getString(3),
                        estado));
            } while (data.moveToNext());
            data.close();

            lvItems = findViewById(R.id.lvItems);
            adaptador = new ListaMedicamentos(this,getdata);
            lvItems.setAdapter(adaptador);
        }catch (SQLException ex){
            Toast.makeText(this,"Error",Toast.LENGTH_SHORT).show();
        }
        boton2 = (Button) findViewById(R.id.button2);
        boton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(AlarmaActivity.this,MenuActivity.class);
                startActivity(intent);
            }
        });
                ingresarBoton = (Button) findViewById(R.id.ingresar);
        ingresarBoton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(AlarmaActivity.this,IngresarActivity.class);
                startActivity(intent);
            }
        });






    }


    public void verLista(View view) {

        CalendarioBaseHelper helper = new CalendarioBaseHelper(this);
        try {
            ArrayList<Medicamento> medicamentos = (ArrayList<Medicamento>) helper.listaMedicamentos();
            Intent intent = new Intent(this, ListaEliminarActivity.class);
            startActivity(intent);
        } catch (Exception ex) {
            Toast.makeText(this, "Lista Vacía", Toast.LENGTH_SHORT).show();
        }

    }
}
