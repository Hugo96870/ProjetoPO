package woo.core;

import woo.app.suppliers.Message;

public class Fornecedor{
    private String _idFornecedor;
    private String _nome;
    private String _morada;
    private boolean _estadoAtividade;

    public Fornecedor(String id, String nome, String morada){
        _idFornecedor = id;
        _nome = nome;
        _morada = morada;
        _estadoAtividade = true;
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
}