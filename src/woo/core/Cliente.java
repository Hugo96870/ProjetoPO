package woo.core;

public class Cliente{
    private String _idCliente;
    private String _nome;
    private String _morada;
    private String _estatuto;
    private int _valorPago;
    private int _valorComprado;
    private int _pontos;
    
    public Cliente(String id, String nome, String morada){
        _idCliente = id;
        _nome = nome;
        _morada = morada;
        _pontos = 0;
        _estatuto="Normal";
        _valorComprado=0;
        _valorPago=0;
    }

    public String toStringCliente(){
        return _idCliente + "|" + _nome + "|" + _morada + "|" + _estatuto + "|" + _valorComprado + "|"
                + _valorPago;
    }
}