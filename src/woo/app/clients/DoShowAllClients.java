package woo.app.clients;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException; 
import pt.tecnico.po.ui.Input; 
import woo.core.StoreManager;  
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
    //FIXME implement command
  }
}
