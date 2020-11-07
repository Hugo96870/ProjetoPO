package woo.app.clients;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;
import woo.app.exception.DuplicateClientKeyException;
import woo.app.exception.UnknownClientKeyException;
import woo.core.StoreManager;
import woo.core.Cliente;
import woo.core.exception.InvalidClientKeyException;
//FIXME import other classes

/**
 * Show client.
 */
public class DoShowClient extends Command<StoreManager> {

  //FIXME add input fields
  private Input<String> _idCliente;

  public DoShowClient(StoreManager storefront) {
    super(Label.SHOW_CLIENT, storefront);
    //FIXME init input fields
    _idCliente = _form.addStringInput(Message.requestClientKey());
  }

  @Override
  public void execute() throws UnknownClientKeyException{
    //FIXME implement command
    _form.parse();
    try {
      Cliente cl = _receiver.getCliente(_idCliente.value());
      _display.popup(cl.toStringCliente());
    } catch (InvalidClientKeyException e) {
      throw new UnknownClientKeyException(e.getMessage());
    }
  }
}
