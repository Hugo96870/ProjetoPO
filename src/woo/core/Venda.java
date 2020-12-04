package woo.core;

import java.io.Serializable;
import woo.app.suppliers.Message;

public class Venda extends Transacao implements Serializable{
    private Produto _produto;
    private int _quantidade;
    private int _dataLimite;
    private String _paga;
    private int _dia;
    private String _idCliente;
    private int _custoBase;
    private double _custofinal;
    private int _dataPagamento;

    public Venda(Produto p, int quantidade, int data, int dia, String idCliente, int custo, int nrID){
        super(nrID);
        _produto = p;
        _quantidade = quantidade;
        _dataLimite = data;
        _paga = Message.no();
        _dia = dia;
        _idCliente = idCliente;
        _custoBase = custo;
    }

    public int getDia(){
        return _dia;
    }

    public String getEstado(){
        return _paga;
    }

    public int getQuantidade(){
        return _quantidade;
    }

    public Produto getProduto(){
        return _produto;
    }

    public int getDataLimite(){
        return _dataLimite;
    }

    public String toStringVenda(){
        if(Message.no().equals(_paga)) {
            return this.getID() + "|" + this._idCliente + "|" + this._produto.getId() + "|" + this._quantidade +
                    "|" + this._custoBase + "|" + Math.round(_custofinal) + "|" + this._dataLimite;
        }
        else {
            return this.getID() + "|" + this._idCliente + "|" + this._produto.getId() + "|" + this._quantidade +
                    "|" + this._custoBase + "|" + Math.round(_custofinal) + "|" + this._dataLimite + "|"
                    + this._dataPagamento;
        }
    }

    public void mudarEstado(){
        _paga = Message.yes();
    }

    public void setDataPagamento(int dia){
        _dataPagamento = dia;
    }

    public void setValorFinal(double valor){
        _custofinal = valor;
    }

    public String getIDCliente(){
        return _idCliente;
    }

}