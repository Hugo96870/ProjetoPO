package woo.app.lookups;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;
import woo.app.exception.UnknownClientKeyException;
import woo.core.StoreManager;
import woo.core.*;

import java.util.Collection;
import java.util.List;
import woo.core.exception.InvalidClientKeyException;

/**
 * Lookup payments by given client.
 */
public class DoLookupPaymentsByClient extends Command<StoreManager> {

  private Input<String> _id;

  public DoLookupPaymentsByClient(StoreManager storefront) {
    super(Label.PAID_BY_CLIENT, storefront);
    _id = _form.addStringInput(Message.requestClientKey());
  }

  @Override
  public void execute() throws DialogException {
    _form.parse();
    try {
      Cliente cl = _receiver.getCliente(_id.value());
      Collection<Venda> vendas = cl.getTransacoes();

      for(Venda v: vendas){
        if("SIM".equals(v.getEstado()))
          _display.addLine(v.toStringVenda());
        _display.display();
      }
    } catch(InvalidClientKeyException e){
      throw new UnknownClientKeyException(_id.value());
    }
  }

}
