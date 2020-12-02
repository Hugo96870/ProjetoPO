package woo.core;

import woo.app.suppliers.Message;

import java.util.Collections;
import java.util.List;
import java.util.LinkedList;

import java.io.Serializable;

public class Fornecedor implements Serializable{
    private String _idFornecedor;
    private String _nome;
    private String _morada;
    private boolean _estadoAtividade;
    private List<String> _produtos;
    private List<Transacao> _transacoes;

    public Fornecedor(String id, String nome, String morada){
        _idFornecedor = id;
        _nome = nome;
        _morada = morada;
        _estadoAtividade = true;
        _transacoes = new LinkedList<>();
        _produtos = new LinkedList<>();
    }

    public String toStringFornecedor(){
        String string = _idFornecedor + "|" + _nome + "|" + _morada + "|";
        if (_estadoAtividade)
            return string + Message.yes();
        else
            return string + Message.no();
    }

    public String getNome(){
        return _nome;
    }

    public String getId(){
        return _idFornecedor;
    }

    public String getMorada(){
        return _morada;
    }
    
    public boolean getEstado(){
        return _estadoAtividade;
    }

    public void adicionarTransacao(Encomenda e){
        _transacoes.add(e);
    }

    public void adicionarProduto(String id){
        _produtos.add(id);
    }

    public List<String> getProdutos(){
        return Collections.unmodifiableList(_produtos);
    }

    public String setEstado(boolean valor){
        _estadoAtividade = valor;
        if(valor == false)
            return Message.transactionsOff(this.getId());
        else
            return Message.transactionsOn(this.getId());
    }
}