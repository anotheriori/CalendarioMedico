package cl.inacap.calendariomedico.modelo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import cl.inacap.calendariomedico.R;

public class CalendarioBaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "CalendarioMedico.bd";
    public static final String TABLE_NAME = "USER";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "NAME";
    public static final String COL_3 = "USERNAME";
    public static final String COL_4 = "PASSWORD";
    public static final String TABLE_NAME2= "MEDICAMENTOS";


    public CalendarioBaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sqlTxt = "create table MEDICAMENTOS("
                + "_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "IDFOTO INTEGER, "
                + "HORA TEXT, "
                + "CANTIDAD TEXT, "
                + "NOMBRE TEXT, "
                + "ESTADO INTEGER);";

        String sqlTxt1 = "create table USER("
                + "_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "NAME TEXT, "
                + "USERNAME TEXT, "
                + "PASSWORD TEXT);";
        sqLiteDatabase.execSQL(sqlTxt1);
        sqLiteDatabase.execSQL(sqlTxt);


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME2);
        onCreate(sqLiteDatabase);

    }

    public boolean insertData() {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("NAME", "Derek");
        contentValues.put("USERNAME", "123456789");
        contentValues.put("PASSWORD", "1234");
        long result = sqLiteDatabase.insert(TABLE_NAME, null, contentValues);

        if (result == -1)
            return false;
        else
            return true;

    }

    public boolean insertData2() {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("IDFOTO", R.drawable.paracetamol);
        contentValues.put("HORA", "08:00");
        contentValues.put("CANTIDAD", "1 Pastilla");
        contentValues.put("NOMBRE", "Paracetamol");
        contentValues.put("ESTADO",1);
        long result = sqLiteDatabase.insert(TABLE_NAME2, null, contentValues);

        if (result == -1)
            return false;
        else
            return true;

    }

    public Cursor getAllData(String username, String pass) {

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor res = sqLiteDatabase.rawQuery("select USERNAME,PASSWORD from user where USERNAME =" + username + " and PASSWORD =" + pass, null);
        return res;
    }
    public Cursor getAllMedicamentos(int Estado){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor data= sqLiteDatabase.rawQuery("select IDFOTO,HORA,CANTIDAD,NOMBRE,ESTADO from medicamentos where ESTADO=1",null);
        return data;
    }
    public void ingresarMedicamento(Medicamento medicamento) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put("IDFOTO", medicamento.getIdFoto());
        valores.put("HORA", medicamento.getHora());
        valores.put("CANTIDAD", medicamento.getCantidad());
        valores.put("NOMBRE", medicamento.getNombre());
        if (medicamento.isEstado()) {
            valores.put("ESTADO", 1);
        } else {
            valores.put("ESTADO", 0);
        }
        db.insert("MEDICAMENTOS", null, valores);
        db.close();


    }

    public List<Medicamento> listaMedicamentos() {
        List<Medicamento> medicamentos = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query("MEDICAMENTOS",
                new String[]{"IDFOTO", "HORA", "CANTIDAD", "NOMBRE", "ESTADO"},
                null, null, null, null,
                null);
        cursor.moveToFirst();
        int estadoInt;
        boolean estado = false;
        do {
            estadoInt = cursor.getInt(4);
            if (estadoInt == 1) estado = true;
            else estado = false;

            medicamentos.add(new Medicamento(cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    estado));
        } while (cursor.moveToNext());
        cursor.close();
        db.close();

        return medicamentos;
    }

   /* public List<Medicamento> GetArrayItems(){
        ArrayList<Medicamento> listItems = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query("MEDICAMNETOS",
                new String[]{"IDFOTO", "HORA", "CANTIDAD", "NOMBRE", "ESTADO"},
                null, null, null, null,
                null);
        cursor.moveToFirst();
        int estadoInt;
        boolean estado = false;
        do {
            estadoInt = cursor.getInt(4);
            if (estadoInt == 1) estado = true;
            else estado = false;

            listItems.add(new Medicamento(cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    estado));
        } while (cursor.moveToNext());
        cursor.close();
        db.close();

        return listItems;
    }*/

       public Medicamento getMedicamento(String nombre) {
        Medicamento m;
        SQLiteDatabase db = getReadableDatabase();
        String sqlTxt = "select IDFOTO, HORA, CANTIDAD, NOMBRE, ESTADO "
                + "from medicamentos where NOMBRE=? ";
        String[] argumentos = new String[]{nombre};
        try {
            Cursor cursor = db.rawQuery(sqlTxt, argumentos);
            cursor.moveToFirst();
            boolean estado = false;
            if (cursor.getInt(4) == 1) estado = true;
            m = new Medicamento(cursor.getInt(0),
                    cursor.getString(1), cursor.getString(2),
                    cursor.getString(3),
                    estado);
        } catch (SQLException ex) {
            m = null;
        }
        return m;
    }
    public String cambiarEstado(Medicamento m)
    {
        int estadoInt;
        //m ya viene con el estado cambiado,
        //solo hay que cambiarlo en la BD.
        if(m.isEstado()) estadoInt=1;
        else estadoInt=0;
        String sqlTxt="UPDATE MEDICAMENTOS SET ESTADO=? "
                +"WHERE NOMBRE=? ";
        Object[] argumentos=new Object[]{estadoInt,m.getNombre()};
        try{
            getWritableDatabase().execSQL(sqlTxt,argumentos);
            return "Se cambió correctamente el estado";
        }catch (SQLException ex){
            return "ERROR: No se pudo cambiar el estado";
        }
    }
    public String eliminarListos()
    {
        String sqlTxt="DELETE FROM MEDICAMENTOS WHERE ESTADO=0";
        try{
            getWritableDatabase().execSQL(sqlTxt);
            return "Se eliminaron todos los productos comprados";
        }
        catch (SQLException ex)
        {
            return "ERROR: No se pueden eliminar los productos";
        }
    }
}
