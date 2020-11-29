package woo.core;

import java.io.Serializable;

import woo.core.exception.ServiceLevelUnknownException;
import woo.core.exception.ServiceTypeUnknownException;

public class Contentor extends Produto implements Serializable{
    private TipoTransporte _tipoTransporte;
    private NivelServico _qualidadeServico;

    public Contentor(String id, int preco, int valorCritico, String fornecedor, String tipo, String qualidade,
                     int quantidade) throws ServiceLevelUnknownException, ServiceTypeUnknownException{
        super(id, preco, valorCritico, quantidade, fornecedor, TipoDeProduto.CONTAINER);
        if("NORMAL".equals(tipo))
            _tipoTransporte = TipoTransporte.NORMAL;
        else if("AIR".equals(tipo))
            _tipoTransporte = TipoTransporte.AIR;
        else if("EXPRESS".equals(tipo))
            _tipoTransporte = TipoTransporte.EXPRESS;
        else if("PERSONAL".equals(tipo))
            _tipoTransporte = TipoTransporte.PERSONAL;
        else
            throw new ServiceTypeUnknownException(tipo);

        if("B4".equals(qualidade))
            _qualidadeServico = NivelServico.B4;
        else if("C4".equals(qualidade))
            _qualidadeServico = NivelServico.C4;
        else if("C5".equals(qualidade))
            _qualidadeServico = NivelServico.C5;
        else if("DL".equals(qualidade))
            _qualidadeServico = NivelServico.DL;
        else
            throw new ServiceLevelUnknownException(qualidade);
    }

    public String toStringProduto(){
        return this.getTipo() + "|" + this.getId() + "|" + this.getFornecedor() + "|" + this.getPreco() +
                "|" + this.getPrecoCritico() + "|" + this.getQuantidade() + "|" + this.getTipoTransporte() + "|" +
                    this.getNivelServico();
    }

    public TipoTransporte getTipoTransporte(){
        return _tipoTransporte;
    }

    public NivelServico getNivelServico(){
        return _qualidadeServico;
    }
}