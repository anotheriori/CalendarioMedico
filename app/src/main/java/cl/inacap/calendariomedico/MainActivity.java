package cl.inacap.calendariomedico;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import cl.inacap.calendariomedico.modelo.CalendarioBaseHelper;

public class MainActivity extends AppCompatActivity {

    CalendarioBaseHelper myDb;
    Button boton;
    EditText run;
    EditText contrasena;
    TextView resultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        myDb = new CalendarioBaseHelper(this);
        boton=(Button)findViewById(R.id.button);
        run=(EditText)findViewById(R.id.run);
        contrasena=(EditText)findViewById(R.id.contrasena);
        resultado=(TextView)findViewById(R.id.textView);
        boolean isInserted= myDb.insertData();
        boolean isInserted2= myDb.insertData2();




        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if ((run.getText().toString().isEmpty()) || (contrasena.getText().toString().isEmpty())) {
                    resultado.setText("Ingrese Run o contraseña");
                } else{


                try {
                    String username=run.getText().toString();
                    String pass = contrasena.getText().toString();
                    Cursor res = myDb.getAllData(username, pass);



                    if (res.getCount() != 0) {
                        Intent intent = new Intent(MainActivity.this, MenuActivity.class);
                        startActivity(intent);

                    }else {
                        resultado.setText("Run o contraseña invalidas");
                    }
                } catch (SQLException ex) {
                    resultado.setText("Error");
                }}


            }});
    }


}
