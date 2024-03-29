package woo.core;

import java.util.LinkedList;
import java.util.List;
import java.io.Serializable;

public class Cliente implements Serializable, Observer{
    private String _idCliente;
    private String _nome;
    private String _morada;
    private String _estatuto;
    private double _valorPago;
    private double _valorComprado;
    private int _pontos;
    private List<Venda> _transacoes;
    private List<String> _notificcoes;
    private int _nrNotificacoes;
    
    public Cliente(String id, String nome, String morada){
        _idCliente = id;
        _nome = nome;
        _morada = morada;
        _pontos = 0;
        _estatuto = "NORMAL";
        _valorComprado = 0;
        _valorPago = 0;
        _transacoes = new LinkedList<>();
        _notificcoes = new LinkedList<>();
        _nrNotificacoes = 0;
    }

    public String toStringCliente(){
        return _idCliente + "|" + _nome + "|" + _morada + "|" + _estatuto + "|" + Math.round(_valorComprado) + "|"
                + Math.round(_valorPago);
    }

    public String getNome(){
        return _nome;
    }

    public String getMorada(){
        return _morada;
    }

    public String getIdCliente(){
        return _idCliente;
    }

    public String getEstatuto(){
        return _estatuto;
    }

    public int getValorPago(){
        return (int)_valorPago;
    }

    public int getValorComprado(){
        return (int)_valorComprado;
    }

    public int getPontos(){
        return _pontos;
    }
    
    public List<Venda> getTransacoes(){
        return _transacoes;
    }

    public void adicionarTransacao(Venda v){
        _transacoes.add(v);
    }

    public void mudarPontos(double pontos){
        _pontos += pontos;
        setEstatuto();
    }

    public void aumentarValorComprado(double valor){
        _valorComprado += valor;
    }

    public void aumentarValorPago(double valor){
        _valorPago += valor;
    }

    public void setEstatuto(){
        if(_pontos > 25000)
            _estatuto = "ELITE";
        else if(2000 < _pontos)
            _estatuto = "SELECTION";
        else
            _estatuto = "NORMAL";
    }

    public void adicionarNotificacao(String mensagem){
        _notificcoes.add(mensagem);
        _nrNotificacoes += 1;
    }

    public String toStringNotificacoes(){
        String mensagem = _notificcoes.get(0);
        _notificcoes.remove(mensagem);
        return mensagem;
    }

    public int getNrNotificacoes(){
        return _nrNotificacoes;
    }

    public void resetNrNotificacoes(){
        _nrNotificacoes = 0;
    }

    public void update(int preco, int quantidade){
    }
}