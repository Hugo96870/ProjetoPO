package woo.core;

public class Venda extends Transacao{
    private Produto _produto;
    private int _quantidade;
    private int _dataPagamento;
    private boolean _paga;
    private int _dia;

    public Venda(Produto p, int quantidade, int data, int dia){
        super();
        _produto = p;
        _quantidade = quantidade;
        _dataPagamento = data;
        _paga = false;
        _dia = dia;
    }

    public int getDia(){
        return _dia;
    }

    public boolean getEstado(){
        return _paga;
    }

    public int getQuantidade(){
        return _quantidade;
    }

    public Produto getProduto(){
        return _produto;
    }

    public int getDataPagamento(){
        return _dataPagamento;
    }

}