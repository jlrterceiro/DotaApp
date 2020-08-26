package com.example.dotaapp;

public class Jogador {
    
    private String jogador1;
    private String jogador2;
    private String partidas1;
    private String partidas2;

    public Jogador(String jogador1, String jogador2, String partidas1, String partidas2) {
        this.jogador1 = jogador1;
        this.jogador2 = jogador2;
        this.partidas1 = partidas1;
        this.partidas2 = partidas2;
    }

    public String getJogador1() {
        return jogador1;
    }

    public void setJogador1(String jogador1) {
        this.jogador1 = jogador1;
    }

    public String getJogador2() {
        return jogador2;
    }

    public void setJogador2(String jogador2) {
        this.jogador2 = jogador2;
    }


    public String getPartidas1() {
        return partidas1;
    }

    public void setPartidas1(String partidas1) {
        this.partidas1 = partidas1;
    }

    public String getPartidas2() {
        return partidas2;
    }

    public void setPartidas2(String partidas2) {
        this.partidas2 = partidas2;
    }
}
