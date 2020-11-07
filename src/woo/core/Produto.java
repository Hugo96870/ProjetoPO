package woo.core;

public class Produto{
    private int _preco;
    private int _precoCritico;
    private String _idProduto;
    private int _quantidade;
    private Fornecedor _fornecedor;
    private TipoDeProduto _tipo;

    public Produto(String id, int preco, int precoCritico, int quantidade, Fornecedor fornecedor, TipoDeProduto tipo){
        _quantidade = quantidade;
        _preco = preco;
        _idProduto = id;
        _precoCritico = precoCritico;
        _fornecedor = fornecedor;
        _tipo = tipo;
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

    public Fornecedor getFornecedor(){
        return _fornecedor;
    }
}