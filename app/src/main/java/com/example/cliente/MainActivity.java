package com.example.cliente;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView txtNombre, txtPass, txtEmail, txtTel;
    Button btnAgregar, btnCancelar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtNombre = (TextView) findViewById(R.id.txtNombre);
        txtPass = (TextView) findViewById(R.id.txtPassword);
        txtEmail = (TextView) findViewById(R.id.txtEmail);
        txtTel = (TextView) findViewById(R.id.txtTel);

        btnAgregar = (Button) findViewById(R.id.btnGuardar);
        btnCancelar = (Button) findViewById(R.id.btnCancelar);

        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombre = txtNombre.getText().toString();
                String pass = txtPass.getText().toString();
                String email = txtEmail.getText().toString();
                String tel = txtTel.getText().toString();

                ContentResolver cr = getContentResolver();
                ContentValues cv = new ContentValues();

                cv.put(MiProveedorContenidoContract.Usuarios.NOMBRE, nombre);
                cv.put(MiProveedorContenidoContract.Usuarios.PASS, pass);
                cv.put(MiProveedorContenidoContract.Usuarios.EMAIL, email);
                cv.put(MiProveedorContenidoContract.Usuarios.TELEFONO, tel);

                Uri uriresult = cr.insert(MiProveedorContenidoContract.Usuarios.CONTENT_URI,
                        cv);
//                int uriresult = cr.update(MiProveedorContenidoContract.Usuarios.CONTENT_URI,
//                        cv, "nombre=?",
//                        new String[]{ String.valueOf(nombre)});
                Log.d("MICP", uriresult.toString());
//                Log.d("MICP", uriresult+"");
                Cursor cursor = getContentResolver().query(
                        MiProveedorContenidoContract.Usuarios.CONTENT_URI,
                        null, null, null, null);
                while (cursor.moveToNext()) {
                    Log.d("MICP", cursor.getInt(0) + " - " + cursor.getString(2));
                }
            }
        });
    }
}