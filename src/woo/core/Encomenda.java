package woo.core;

import woo.core.*;
import java.util.List;
import java.util.LinkedList;

public class Encomenda extends Transacao{
    private Fornecedor _fornecedor;
    private List<String> _produtos;
    private List<Integer> _quantidades;
    private int _custoTotal = 0;

    public Encomenda(List<String> produtos, Fornecedor f,List<Integer> quantidades, int custo){
        super();
        _produtos = produtos;
        _quantidades = quantidades;
        _fornecedor = f;
        _custoTotal = custo;
    }

    public Fornecedor getFornecedor(){
        return _fornecedor;
    }

}