package com.example.carolinacabral.realm.Domain;

/**
 * Created by Carolina Cabral on 13/11/2017.
 */
import android.content.Intent;

import io.realm.RealmObject;
import io.realm.annotations.Index;
import io.realm.annotations.PrimaryKey;

public class Produto extends RealmObject{
    public static final String ID = "com.example.carolinacabral.domain.realm.RealmObject.ID";
    @PrimaryKey
    private int id;
    private String cliente;
    private String descricao;
    private int quantidade;
    private int preco;
    private String pagamento;

    public Produto(int id, String cliente, String descricao, int quantidade, int preco, String pagamento) {
        this.id = id;
        this.cliente = cliente;
        this.descricao = descricao;
        this.quantidade = quantidade;
        this.preco = preco;
        this.pagamento = pagamento;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public int getPreco() {
        return preco;
    }

    public void setPreco(int preco) {
        this.preco = preco;
    }

    public String getPagamento() {
        return pagamento;
    }

    public void setPagamento(String pagamento) {
        this.pagamento = pagamento;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }



   /* public int precoTotal ()
    {

        return preco*quantidade;


    }*/
    public Produto()
    {
        super();
    }

}
