package com.example.dotaapp;

import android.os.AsyncTask;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Scanner;

public class HttpServiceJogador extends AsyncTask<Void, Void, Jogador> {

    private final String idJogador1;
    private final String idJogador2;

    public HttpServiceJogador(String idJogador1, String idJogador2) {
        this.idJogador1 = idJogador1;
        this.idJogador2 = idJogador2;
    }

    @Override
    protected Jogador doInBackground(Void... voids) {
        try {
            URL urlJog1 = new URL("https://api.opendota.com/api/players/" + this.idJogador1);
            URL urlJog2 = new URL("https://api.opendota.com/api/players/" + this.idJogador2);
            URL urlPar1 = new URL("https://api.opendota.com/api/players/" + this.idJogador1 + "/matches?sort=start_time");
            URL urlPar2 = new URL("https://api.opendota.com/api/players/" + this.idJogador2 + "/matches?sort=start_time");

            HttpURLConnection conJog1 = (HttpURLConnection) urlJog1.openConnection();
            conJog1.setRequestMethod("GET");
            conJog1.setRequestProperty("Content-type", "application/json");
            conJog1.setRequestProperty("Accept", "application/json");
            conJog1.setDoOutput(true);
            conJog1.setConnectTimeout(5000);

            HttpURLConnection conJog2 = (HttpURLConnection) urlJog2.openConnection();
            conJog2.setRequestMethod("GET");
            conJog2.setRequestProperty("Content-type", "application/json");
            conJog2.setRequestProperty("Accept", "application/json");
            conJog2.setDoOutput(true);
            conJog2.setConnectTimeout(5000);

            HttpURLConnection conPar1 = (HttpURLConnection) urlPar1.openConnection();
            conPar1.setRequestMethod("GET");
            conPar1.setRequestProperty("Content-type", "application/json");
            conPar1.setRequestProperty("Accept", "application/json");
            conPar1.setDoOutput(true);
            conPar1.setConnectTimeout(5000);

            HttpURLConnection conPar2 = (HttpURLConnection) urlPar2.openConnection();
            conPar2.setRequestMethod("GET");
            conPar2.setRequestProperty("Content-type", "application/json");
            conPar2.setRequestProperty("Accept", "application/json");
            conPar2.setDoOutput(true);
            conPar2.setConnectTimeout(5000);

            Scanner scanner;

            conJog1.connect();
            StringBuilder jogador1 = new StringBuilder();
            scanner = new Scanner(urlJog1.openStream());
            while (scanner.hasNext()) {
                jogador1.append(scanner.next());
            }

            conJog2.connect();
            StringBuilder jogador2 = new StringBuilder();
            scanner = new Scanner(urlJog2.openStream());
            while (scanner.hasNext()) {
                jogador2.append(scanner.next());
            }

            conPar1.connect();
            StringBuilder partidas1 = new StringBuilder();
            scanner = new Scanner(urlPar1.openStream());
            while (scanner.hasNext()) {
                partidas1.append(scanner.next());
            }

            conPar2.connect();
            StringBuilder partidas2 = new StringBuilder();
            scanner = new Scanner(urlPar2.openStream());
            while (scanner.hasNext()) {
                partidas2.append(scanner.next());
            }


            Jogador jogador = new Jogador(jogador1.toString(), jogador2.toString(), partidas1.toString(), partidas2.toString());
            return jogador;





        } catch (MalformedURLException | ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
