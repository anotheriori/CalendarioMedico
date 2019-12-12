package cl.inacap.calendariomedico;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

import cl.inacap.calendariomedico.modelo.CalendarioBaseHelper;
import cl.inacap.calendariomedico.modelo.Medicamento;

public class ListaEliminarActivity extends ListActivity {
    private ListView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        cargarLista();
    }

    public void cargarLista()
    {
        lista=getListView();

        CalendarioBaseHelper helper=new CalendarioBaseHelper(this);
        List<Medicamento> medicamentoList=helper.listaMedicamentos();


        ArrayAdapter<Medicamento> listaAdapter= new ArrayAdapter<Medicamento>(this,
                android.R.layout.simple_expandable_list_item_1,medicamentoList);
        lista.setAdapter(listaAdapter);
    }

    @Override
    public void onListItemClick(ListView listView, View view, int posicion, long id)
    {
        Object o=lista.getItemAtPosition(posicion); //item seleccionado
        String linea=o.toString(); //El texto del item seleccionado
        //Obtener el nombre
        String[] separar=linea.split(":");
        //Llamar a DetallesActivity


        Intent intent=new Intent(ListaEliminarActivity.this,DetalleActivity.class);

        intent.putExtra("nombreMedicamento",separar[0]);


        startActivityForResult(intent,1);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode,resultCode,data);
        if(requestCode==1)
        {
            if(resultCode==RESULT_OK)
            {
                cargarLista();
            }
        }
    }

}
