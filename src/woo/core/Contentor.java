package woo.core;

import java.io.Serializable;

import woo.core.exception.ServiceLevelUnknownException;
import woo.core.exception.ServiceTypeUnknownException;

import java.io.SerializablePermission;

public class Contentor extends Produto implements Serializable{
    private TipoTransporte _tipoTransporte;
    private NivelServico _qualidadeServico;

    public Contentor(String id, int preco, int valorCritico, String fornecedor, String tipo, String qualidade, int quantidade)
                        throws ServiceLevelUnknownException, ServiceTypeUnknownException{
        super(id, preco, valorCritico, quantidade, fornecedor, TipoDeProduto.CONTAINER);
        if(tipo.equals("NORMAL"))
            _tipoTransporte = TipoTransporte.NORMAL;
        else if(tipo.equals("AIR"))
            _tipoTransporte = TipoTransporte.AIR;
        else if(tipo.equals("EXPRESS"))
            _tipoTransporte = TipoTransporte.EXPRESS;
        else if(tipo.equals("PERSONAL"))
            _tipoTransporte = TipoTransporte.PERSONAL;
        else
            throw new ServiceTypeUnknownException(tipo);
        if(qualidade.equals("B4"))
            _qualidadeServico = NivelServico.B4;
        else if(qualidade.equals("C4"))
            _qualidadeServico = NivelServico.C4;
        else if(qualidade.equals("C5"))
            _qualidadeServico = NivelServico.C5;
        else if(qualidade.equals("DL"))
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