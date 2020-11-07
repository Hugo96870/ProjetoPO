package woo.app.products;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;
import woo.core.StoreManager;
//FIXME import other classes

/**
 * Change product price.
 */
public class DoChangePrice extends Command<StoreManager> {

  //FIXME add input fields
  
  public DoChangePrice(StoreManager receiver) {
    super(Label.CHANGE_PRICE, receiver);
    //FIXME init input fields
  }

  @Override
  public final void execute() throws DialogException {
    //FIXME implement command
  }
}
