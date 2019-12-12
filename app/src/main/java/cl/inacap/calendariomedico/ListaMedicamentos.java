package cl.inacap.calendariomedico;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cl.inacap.calendariomedico.modelo.Medicamento;

public class ListaMedicamentos extends BaseAdapter {

    public Context context;
    public ArrayList<Medicamento> listItems;


    public ListaMedicamentos(Context context, ArrayList<Medicamento> listItems) {
        this.context = context;
        this.listItems = listItems;
    }

    @Override
    public int getCount() {
        return listItems.size();
    }

    @Override
    public Object getItem(int i) {
        return listItems.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        Medicamento item = (Medicamento) getItem(i);
        convertView= LayoutInflater.from(context).inflate(R.layout.item, null);
        ImageView imgFoto = convertView.findViewById(R.id.imgFoto);
        TextView tvTitulo = convertView.findViewById(R.id.tvTitulo);
        TextView tvName = convertView.findViewById(R.id.tvName);
        TextView tvContenido = convertView.findViewById(R.id.tvContenido);

        imgFoto.setImageResource(item.getIdFoto());
        tvTitulo.setText(item.getHora());
        tvName.setText(item.getNombre());
        tvContenido.setText(item.getCantidad());
        return convertView;
    }


}
