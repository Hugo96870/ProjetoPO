package woo.core;

import woo.app.exception.UnknownServiceTypeException;
import woo.core.exception.ServiceTypeUnknownException;
import java.io.Serializable;
import java.security.Provider;

public class Caixa extends Produto implements Serializable{
    private TipoTransporte _tipoTransporte;

    public Caixa(String id, int preco, int valorCritico, String fornecedor, String tipo, int quantidade)
                    throws ServiceTypeUnknownException {
        super(id, preco, valorCritico, quantidade, fornecedor, TipoDeProduto.BOX);
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
    }

    public String toStringProduto() {
        return this.getTipo() + "|" + this.getId() + "|" + this.getFornecedor() + "|" + this.getPreco() +
                "|" + this.getPrecoCritico() + "|" + this.getQuantidade() + "|" + this.getTipoTransporte();
    }

    public TipoTransporte getTipoTransporte(){
        return _tipoTransporte;
    }
}