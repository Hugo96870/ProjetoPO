package woo.app.clients;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;
import woo.app.exception.UnknownClientKeyException;
import woo.core.StoreManager;
import woo.core.*;
import woo.core.exception.InvalidClientKeyException;
import java.util.List;

/**
 * Show all transactions for a specific client.
 */
public class DoShowClientTransactions extends Command<StoreManager> {

  private Input<String> _id;

  public DoShowClientTransactions(StoreManager storefront) {
    super(Label.SHOW_CLIENT_TRANSACTIONS, storefront);
    _id = _form.addStringInput(Message.requestClientKey());
  }

  @Override
  public void execute() throws DialogException {
    _form.parse();
    try {
      Cliente cl = _receiver.getCliente(_id.value());
      List<Transacao> _transacoes = cl.getTransacoes();
      for(Transacao t: _transacoes){
        if(_receiver.getStore().getEncomendas().contains(t))
          _display.popup(t.toStringEncomenda());
        if(_receiver.getStore().getVendas().contains(t))
          _display.popup(t.toStringVenda());
      }
    }catch (InvalidClientKeyException e){
      throw new UnknownClientKeyException(_id.value());
    }

  }

}
