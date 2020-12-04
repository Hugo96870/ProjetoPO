package woo.app.transactions;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;
import woo.app.exception.UnknownClientKeyException;
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
  public final void execute() throws UnknownClientKeyException{
    _form.parse();

    Collection<Venda> _vendas = _receiver.getVendas();

    for (Venda v : _vendas) {
      if (v.getID() == _id.value()) {
        if("NAO".equals(v.getEstado())) {
          try {
            double valorReal = _receiver.pagar(v);
            v.setValorFinal(valorReal);
            v.mudarEstado();
            v.setDataPagamento(_receiver.getData());
          } catch(InvalidClientKeyException e){
            throw new UnknownClientKeyException(e.getMessage());
          }
        }
        break;
      }
    }
  }

}
