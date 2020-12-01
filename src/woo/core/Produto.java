package woo.core;

import java.io.Serializable;

public abstract class Produto implements Serializable {
    private int _preco;
    private int _precoCritico;
    private String _idProduto;
    private int _quantidade;
    private String _idFornecedor;
    private TipoDeProduto _tipo;

    public Produto(String id, int preco, int precoCritico, int quantidade, String idFornecedor, TipoDeProduto tipo){
        _quantidade = quantidade;
        _preco = preco;
        _idProduto = id;
        _precoCritico = precoCritico;
        _idFornecedor = idFornecedor;
        _tipo = tipo;
    }

    public String toStringProduto(){
        return _tipo + "|" + _idProduto + "|" + _idFornecedor + "|" + _preco +
                "|" + _precoCritico + "|" + _quantidade + "|";
    }

    public int getPreco(){
        return _preco;
    }

    public int getPrecoCritico(){
        return _precoCritico;
    }

    public int getQuantidade(){
        return _quantidade;
    }

    public String getId(){
        return _idProduto;
    }

    public String getFornecedor(){
        return _idFornecedor;
    }

    public TipoDeProduto getTipo(){
        return _tipo;
    }

    public void mudarPreco(int preco){
        _preco = preco;
    }

    public void removerQuantidade(int quantidade){
        _quantidade-=quantidade;
    }
}