package com.example.dotaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;
import java.util.concurrent.ExecutionException;

public class InformacoesJogadores extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informacoes_jogadores);

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        String idJogador1 = intent.getStringExtra(MainActivity.EXTRA_JOGADOR_1);
        String idJogador2 = intent.getStringExtra(MainActivity.EXTRA_JOGADOR_2);

        try {

            Jogador jogador = new HttpServiceJogador(idJogador1, idJogador2).execute().get();

            TextView textView1 = findViewById(R.id.textView1);
            TextView textView2 = findViewById(R.id.textView2);
            TextView textViewPartidas = findViewById(R.id.textViewPartidas);

            JSONObject jsonObjJog1 = new JSONObject(jogador.getJogador1());
            JSONObject jsonObjJog2 = new JSONObject(jogador.getJogador2());
            JSONArray jsonArrPar1 = new JSONArray(jogador.getPartidas1());
            JSONArray jsonArrPar2 = new JSONArray(jogador.getPartidas2());

            Set conj = new HashSet<String>();
            String partidaId;
            for (int i = 0; i < jsonArrPar1.length(); i++) {
                JSONObject x = jsonArrPar1.getJSONObject(i);
                partidaId = x.getString("match_id");
                conj.add(partidaId);
            }

            List partidasJuntos = new ArrayList<String>();
            List datas = new ArrayList<String>();

            for (int i = 0; i < jsonArrPar2.length(); i++) {
                partidaId = jsonArrPar2.getJSONObject(i).getString("match_id");
                if (conj.contains(partidaId) == true ) {
                    Date time = new java.util.Date( (Long.parseLong(jsonArrPar2.getJSONObject(i).getString("start_time")))*1000);
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyy HH:mm");
                    sdf.setTimeZone(TimeZone.getTimeZone("UTC+3"));

                    partidasJuntos.add(partidaId);
                    datas.add(sdf.format(time));
                }
            }

            String mess1, mess2, messPar;

            JSONObject profile1 = jsonObjJog1.getJSONObject("profile");
            JSONObject profile2 = jsonObjJog2.getJSONObject("profile");

            mess1 = "Jogador 1\n";
            mess1 += "ID: " + profile1.getString("account_id") + "\n";
            mess1 += "Nome: " + profile1.getString("personaname") + "\n";
            mess1 += "Partidas: " + jsonArrPar1.length() + "\n";

            mess2 = "Jogador 2\n";
            mess2 += "ID: " + profile2.getString("account_id") + "\n";
            mess2 += "Nome: " + profile2.getString("personaname") + "\n";
            mess2 += "Partidas: " + jsonArrPar2.length() + "\n";

            messPar = "Partidas Juntos: " + partidasJuntos.size() + "\n\n";
            for (int i=partidasJuntos.size()-1; i>=0; i--) {
                messPar += datas.get(i) + " - ID da Partida: " + partidasJuntos.get(i) + "\n";
            }

            textView1.setText(mess1);
            textView2.setText(mess2);
            textViewPartidas.setText(messPar);
            
            
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
