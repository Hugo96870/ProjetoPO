package woo.core;

import woo.core.exception.ServiceTypeUnknownException;
import java.io.Serializable;

public class Caixa extends Produto implements Serializable{
    private TipoTransporte _tipoTransporte;

    public Caixa(String id, int preco, int valorCritico, String fornecedor, String tipo, int quantidade)
                    throws ServiceTypeUnknownException {
        super(id, preco, valorCritico, quantidade, fornecedor, TipoDeProduto.BOX);
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
    }

    public String toStringProduto() {
        return this.getTipo() + "|" + this.getId() + "|" + this.getFornecedor() + "|" + this.getPreco() +
                "|" + this.getPrecoCritico() + "|" + this.getQuantidade() + "|" + this.getTipoTransporte();
    }

    public TipoTransporte getTipoTransporte(){
        return _tipoTransporte;
    }
}