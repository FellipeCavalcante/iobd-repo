package com.dev.fellipe.redirectUrlShortner.negocio;

import java.sql.Timestamp;

public class Anotacao {
    private int id;
    private String titulo;
    private Timestamp dataHora;
    private String descricao;
    private String cor;
    private byte[] foto;

    public Anotacao() {
    }

    public Anotacao(int id, String titulo, Timestamp dataHora, String descricao, String cor, byte[] foto) {
        this.id = id;
        this.titulo = titulo;
        this.dataHora = dataHora;
        this.descricao = descricao;
        this.cor = cor;
        this.foto = foto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Timestamp getDataHora() {
        return dataHora;
    }

    public void setDataHora(Timestamp dataHora) {
        this.dataHora = dataHora;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    @Override
    public String toString() {
        return "Anotacao{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", dataHora=" + dataHora +
                ", descricao='" + descricao + '\'' +
                ", cor='" + cor + '\'' +
                ", foto=" + (foto != null ? "[imagem]" : "null") +
                '}';
    }
}
