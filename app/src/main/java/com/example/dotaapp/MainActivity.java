package com.example.dotaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_JOGADOR_1 = "com.example.myfirstapp.jogador1";
    public static final String EXTRA_JOGADOR_2 = "com.example.myfirstapp.jogador2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void sendMessage(View view) {
        Intent intent = new Intent(this, InformacoesJogadores.class);
        EditText editText1 = (EditText) findViewById(R.id.editText1);
        EditText editText2 = (EditText) findViewById(R.id.editText2);
        String idJogador1 = editText1.getText().toString();
        String idJogador2 = editText2.getText().toString();

       // idJogador1 = "131401396";
        //hjidJogador2 = "163250894";
        //idJogador2 = "297995725";

       // idJogador1 = "172548387";
       // idJogador2 = "130195289";


        intent.putExtra(EXTRA_JOGADOR_1, idJogador1);
        intent.putExtra(EXTRA_JOGADOR_2, idJogador2);
        startActivity(intent);
    }

}
