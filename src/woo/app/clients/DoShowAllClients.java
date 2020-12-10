package woo.app.clients;

import java.util.*;
import pt.tecnico.po.ui.Command;
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
  public void execute(){
    Collection<Cliente> clientesAux = _receiver.getTodosClientes();
    for(Cliente cl: clientesAux){
      _display.addLine(cl.toStringCliente());
    }
    _display.display();
  }
}
