package woo.core;
import java.io.Serializable;
import woo.core.*;
import java.util.List;
import java.util.LinkedList;

public class Encomenda extends Transacao implements Serializable{
    private Fornecedor _fornecedor;
    private List<String> _produtos;
    private List<Integer> _quantidades;
    private int _custoTotal = 0;
    private int _data;

    public Encomenda(List<String> produtos, Fornecedor f,List<Integer> quantidades, int custo, int data){
        super();
        _produtos = produtos;
        _quantidades = quantidades;
        _fornecedor = f;
        _custoTotal = custo;
        _data = data;
    }

    public String toStringEncomenda(){
        return this.getID() + "|" + this._fornecedor.getId() + "|" + this._custoTotal + "|" + this._data;
    }

    public Fornecedor getFornecedor(){
        return _fornecedor;
    }

    public List<String> getProdutos(){
        return _produtos;
    }

}