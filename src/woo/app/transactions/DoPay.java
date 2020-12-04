package woo.app.transactions;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;
import woo.app.exception.UnknownClientKeyException;
import woo.app.exception.UnknownTransactionKeyException;
import woo.core.*;
import woo.core.exception.InvalidClientKeyException;

import javax.print.DocFlavor;
import java.util.Collection;
//FIXME import other classes

/**
 * Pay transaction (sale).
 */
public class DoPay extends Command<StoreManager> {

  private Input<Integer> _id;

  public DoPay(StoreManager storefront) {
    super(Label.PAY, storefront);
    _id = _form.addIntegerInput(Message.requestTransactionKey());
  }

  @Override
  public final void execute() throws UnknownClientKeyException, UnknownTransactionKeyException{
    _form.parse();
    int i = 0;
    Collection<Venda> _vendas = _receiver.getVendas();
    Collection<Encomenda> _encomendas = _receiver.getEncomendas();

    for(Venda vd: _vendas){
      if(vd.getID() == _id.value())
        i = 1;
    }

    for(Encomenda e: _encomendas){
      if(e.getID() == _id.value())
        i = 2;
    }

    if(i == 0){
      throw new UnknownTransactionKeyException(_id.value());
    }

    else if(i == 1) {
      for (Venda v : _vendas) {
        if (v.getID() == _id.value()) {
          if ("NÃO".equals(v.getEstado())) {
            try {
              double valorReal = _receiver.pagar(v);
              v.mudarEstado();
              v.setDataPagamento(_receiver.getData());
              Cliente c = _receiver.getCliente(v.getIDCliente());
              v.setValorFinal(valorReal);
              c.aumentarValorPago(valorReal);
            } catch (InvalidClientKeyException e) {
              throw new UnknownClientKeyException(e.getMessage());
            }
          }
          break;
        }
      }
    }
  }

}
