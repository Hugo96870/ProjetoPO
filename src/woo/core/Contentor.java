package woo.core;

public class Contentor{
    private String _idContentor;
    private int _preco;
    private int _valorCritico;
    private String _idFornecedorContentor;
    private tipoTransporte _tipoTransporte;
    private qualidadeServico _qualidadeServico;

    public Contentor(String id, int preco, int valorCritico, String fornecedor, tipoTransporte tipo, qualidadeServico qualidade){
        _idContentor = id;
        _preco = preco;
        _valorCritico = valorCritico;
        _idFornecedorContentor = fornecedor;
        _tipoTransporte = tipo;
        _qualidadeServico = qualidade;
    }

    public int getPreco(){
        return _preco;
    }

    public int getValorCritico(){
        return _valorCritico;
    }

    public String getIdContentor(){
        return _idContentor;
    }

    public String getIdFornecedor(){
        return _idFornecedorContentor;
    }

    public tipoTransporte getTipoTransporte(){
        return _tipoTransporte;
    }

    public qualidadeServico getQualidadeServico(){
        return _qualidadeServico;
    }
}