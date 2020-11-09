package woo.app.suppliers;

import pt.tecnico.po.ui.Command;
import woo.core.Fornecedor;
import woo.core.StoreManager;
import java.util.Collection;

/**
 * Show all suppliers.
 */
public class DoShowSuppliers extends Command<StoreManager> {

  public DoShowSuppliers(StoreManager receiver) {
    super(Label.SHOW_ALL_SUPPLIERS, receiver);
  }

  @Override
  public void execute() {
    Collection<Fornecedor> fornecedores = _receiver.getTodosFornecedores();
    for(Fornecedor fr: fornecedores){
      _display.addLine(fr.toStringFornecedor());
    }
    _display.display();
  }
}
