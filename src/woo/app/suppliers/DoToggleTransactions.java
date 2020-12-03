package woo.app.suppliers;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;
import woo.app.exception.UnknownSupplierKeyException;
import woo.core.StoreManager;
import woo.core.exception.SupplierUnknownException;
//FIXME import other classes

/**
 * Enable/disable supplier transactions.
 */
public class DoToggleTransactions extends Command<StoreManager> {

  private Input<String> _id;

  public DoToggleTransactions(StoreManager receiver) {
    super(Label.TOGGLE_TRANSACTIONS, receiver);
    _id = _form.addStringInput(Message.requestSupplierKey());
  }

  @Override
  public void execute() throws UnknownSupplierKeyException {
    _form.parse();
    try {
      String s = _receiver.mudarEstadoFornecedor(_id.value());
      _display.addLine(s);
      _display.display();
    } catch(SupplierUnknownException e){
      throw new UnknownSupplierKeyException(_id.value());
    }
  }

}
