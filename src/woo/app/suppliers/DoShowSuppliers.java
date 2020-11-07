package woo.app.suppliers;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;
import woo.core.Fornecedor;
import woo.core.StoreManager;
import java.util.Collection;

import java.util.Collection;
//FIXME import other classes

/**
 * Show all suppliers.
 */
public class DoShowSuppliers extends Command<StoreManager> {

  //FIXME add input fields

  public DoShowSuppliers(StoreManager receiver) {
    super(Label.SHOW_ALL_SUPPLIERS, receiver);
    //FIXME init input fields
  }

  @Override
  public void execute() {
    Collection<Fornecedor> fornecedores = _receiver.getTodosFornecedores();
    for(Fornecedor fr: fornecedores){
      _display.addLine(fr.toStringFornecedor());
    }
    _display.display();
    //FIXME implement command
  }
}
