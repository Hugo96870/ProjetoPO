package woo.app.clients;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.Input;
import woo.app.exception.UnknownClientKeyException;
import woo.core.StoreManager;
import woo.core.Cliente;
import woo.core.exception.InvalidClientKeyException;

/**
 * Show client.
 */
public class DoShowClient extends Command<StoreManager> {

  private Input<String> _idCliente;

  public DoShowClient(StoreManager storefront) {
    super(Label.SHOW_CLIENT, storefront);
    _idCliente = _form.addStringInput(Message.requestClientKey());
  }

  @Override
  public void execute() throws UnknownClientKeyException{
    _form.parse();
    try {
      Cliente cl = _receiver.getCliente(_idCliente.value());
      _display.popup(cl.toStringCliente());
    } catch (InvalidClientKeyException e) {
      throw new UnknownClientKeyException(e.getMessage());
    }
  }
}
