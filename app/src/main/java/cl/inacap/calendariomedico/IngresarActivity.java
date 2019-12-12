package cl.inacap.calendariomedico;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import cl.inacap.calendariomedico.modelo.CalendarioBaseHelper;
import cl.inacap.calendariomedico.modelo.Medicamento;

public class IngresarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingresar);
    }

    public void ingresarMedicamento(View view) {
        String nombre = ((Spinner) findViewById(R.id.idFoto)).getSelectedItem().toString();
        String hora = ((Spinner) findViewById(R.id.hora)).getSelectedItem().toString();
        String cantidadStr = ((TextView) findViewById(R.id.ingresarCantidad)).getText().toString();

        int idFoto = 0;

        if (nombre.equals("Paracetamol")) {
            idFoto = R.drawable.paracetamol;
        } else if (nombre.equals("Dipirona")) {
            idFoto = R.drawable.dipirona;
        } else {
            idFoto = R.drawable.salbutamol;
        }

        Medicamento medicamento = new Medicamento(idFoto, hora, cantidadStr, nombre);
        CalendarioBaseHelper helper = new CalendarioBaseHelper(this);
        helper.ingresarMedicamento(medicamento);


        finish();
        Intent intent = new Intent(IngresarActivity.this, AlarmaActivity.class);

        startActivity(intent);
    }




    }


