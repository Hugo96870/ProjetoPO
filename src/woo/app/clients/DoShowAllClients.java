package woo.app.clients;

import java.util.*;
import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;
import woo.core.StoreManager;
import woo.core.Cliente;

/**
 * Show all clients.
 */
public class DoShowAllClients extends Command<StoreManager> {

  public DoShowAllClients(StoreManager storefront) {
    super(Label.SHOW_ALL_CLIENTS, storefront);
  }

  @Override
  public void execute() throws DialogException {
    Collection<Cliente> clientesAux = _receiver.getTodosClientes();
    for(Cliente cl: clientesAux){
      _display.addLine(cl.toStringCliente());
    }
    _display.display();
  }
}
