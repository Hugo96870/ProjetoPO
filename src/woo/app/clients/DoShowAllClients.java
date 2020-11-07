package woo.app.clients;

import java.util.Collection;
import java.util.Map;
import java.util.HashMap;
import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException; 
import pt.tecnico.po.ui.Input; 
import woo.core.StoreManager;
import woo.core.Cliente;
import java.util.HashMap;
//FIXME import other classes

/**
 * Show all clients.
 */
public class DoShowAllClients extends Command<StoreManager> {

  //FIXME add input fields

  public DoShowAllClients(StoreManager storefront) {
    super(Label.SHOW_ALL_CLIENTS, storefront);
                //FIXME init input fields
  }

  @Override
  public void execute() throws DialogException {
    Collection<Cliente> clientesAux = _receiver.getTodosClientes();
    for(Cliente cl : clientesAux){
      _display.addLine(cl.toString());
    }
    _display.display();
    //FIXME implement command
  }
}
