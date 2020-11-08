package woo.core;

public class Caixa extends Produto{
    private String _idFornecedorCaixa;
    private tipoTransporte _tipoTransporte;

    public Caixa(String id, int preco, int valorCritico, String fornecedor, tipoTransporte tipo){~
        super(id, preco, valorCritico, )
        _idCaixa = id;
        _preco = preco;
        _valorCritico = valorCritico;
        _idFornecedorCaixa = fornecedor;
        _tipoTransporte = tipo;
    }

    public String getIdCaixa(){
        return _idCaixa;
    }

    public String getIdFornecedor(){
        return _idFornecedorCaixa;
    }

    public tipoTransporte getTipoTransporte(){
        return _tipoTransporte;
    }
}