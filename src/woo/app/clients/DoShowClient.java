package woo.app.clients;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;
import woo.core.StoreManager;
import woo.core.Cliente;
//FIXME import other classes

/**
 * Show client.
 */
public class DoShowClient extends Command<StoreManager> {

  //FIXME add input fields

  public DoShowClient(StoreManager storefront) {
    super(Label.SHOW_CLIENT, storefront);
    //FIXME init input fields
  }

  @Override
  public void execute() throws DialogException {
    //FIXME implement command
  }

}
