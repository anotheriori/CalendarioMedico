package cl.inacap.calendariomedico;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import cl.inacap.calendariomedico.modelo.CalendarioBaseHelper;
import cl.inacap.calendariomedico.modelo.Medicamento;

public class DetalleActivity extends AppCompatActivity {
    private Medicamento medicamento;
    private Intent intent;
    private CalendarioBaseHelper helper=new CalendarioBaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);

        //Obtener el producto
        intent=getIntent();

        //Leer el nombre del producto
        String nombreMedicamento=(String) intent.getExtras().get("nombreMedicamento");

        //Traer el producto desde la base de datos
        medicamento=helper.getMedicamento(nombreMedicamento);


        //Mostrar la información del producto
        TextView hora =(TextView) findViewById(R.id.tvTitulo);
        hora.setText(medicamento.getHora());

        TextView cantidad=(TextView) findViewById(R.id.tvContenido);
        cantidad.setText(medicamento.getCantidad());

        TextView nombre=(TextView)findViewById(R.id.tvName);
        nombre.setText(medicamento.getNombre());


        TextView txtEstado=(TextView)findViewById(R.id.tvEstado);
        Button cambiar=(Button) findViewById(R.id.estado);
        if(medicamento.isEstado())
        {
            txtEstado.setText("Activa");
            cambiar.setText("Marcar como tomado");
        }
        else {
            txtEstado.setText("Tomado");
            cambiar.setText("Marcar como activa");
        }

    }






    public void cambiarEstado(View view)
    {
        medicamento.setEstado(!medicamento.isEstado());
        //Actualizar el estado en la base de datos
        String mensaje=helper.cambiarEstado(medicamento);
        Toast.makeText(this,mensaje,Toast.LENGTH_SHORT).show();

                setResult(RESULT_OK,intent);
                finish();
                }
                }
