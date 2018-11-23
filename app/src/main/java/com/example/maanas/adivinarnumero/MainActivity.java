package com.example.maanas.adivinarnumero;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
Button btn_jugar, btn_comprobar;
EditText et_num_maximo, et_respuesta;
boolean jugando=false;
int num_aleatorio=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cargarvistas();
        cargarOyentes();
    }

    private void cargarOyentes() {
        View.OnClickListener oyente_jugar=new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jugar();
            }
        };
        View.OnClickListener oyente_comprobar=new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                comprobar();
            }
        };
        TextWatcher observador_et_numero_maximo=new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                comprobarCampoJugar();
            }
        } ;
        TextWatcher observador_et_jugar=new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                comprobarCampoRespuesta();
            }
        };
        btn_jugar.setOnClickListener(oyente_jugar);
        btn_comprobar.setOnClickListener(oyente_comprobar);

        et_num_maximo.addTextChangedListener(observador_et_numero_maximo);
        et_respuesta.addTextChangedListener(observador_et_jugar);
    }

    private void comprobar() {
        String resp_usr=et_respuesta.getText().toString();
        int resp_usuario=Integer.parseInt(resp_usr);
        String texto="";
        if (resp_usuario==num_aleatorio)
        {
            texto="Enhorabuena, has acertado";
            //Acciones conducentes a volver a jugar

        }
        else if(resp_usuario<num_aleatorio)
        {
            texto="No, el número es mayor";
        }
        else if(resp_usuario>num_aleatorio)
        {
            texto="No, el número es menor";
        }
        Toast.makeText(this, texto, Toast.LENGTH_LONG).show();
        et_respuesta.setText("");
    }

    private void jugar() {
        jugando=true;
        //habilito et_respuesta
        et_respuesta.setEnabled(true);
        //Pienso un número
        num_aleatorio=crearAleatorio();

        //Saco toast para que inicie
        String txt="Adivina un número entre 0 y "+et_num_maximo.getText().toString();
        Toast.makeText(this, txt, Toast.LENGTH_LONG).show();
    }

    private int crearAleatorio() {
        double d=Math.random();
        String n_maximo=et_num_maximo.getText().toString();
        int num_maximo=Integer.parseInt(n_maximo);
        double aleatorio=d*num_maximo;
        int n_aleatorio=(int)aleatorio;
        return n_aleatorio;
    }

    private void comprobarCampoRespuesta() {
        if(et_respuesta.getText().toString().equals(""))
        {
            btn_comprobar.setEnabled(false);
        }
        else
        {
            btn_comprobar.setEnabled(true);
        }
    }

    private void comprobarCampoJugar() {
        if (et_num_maximo.getText().toString().equals(""))
        {
            btn_jugar.setEnabled(false);
        }
        else
        {
            btn_jugar.setEnabled(true);
        }
    }

    private void cargarvistas() {
        btn_jugar=findViewById(R.id.btn_jugar);
        btn_comprobar=findViewById(R.id.btn_comprobar);
        et_num_maximo=findViewById(R.id.et_maximo);
        et_respuesta=findViewById(R.id.et_respuesta);

    }
}
