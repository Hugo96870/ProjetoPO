package woo.app.clients;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.Input;
import woo.app.exception.UnknownClientKeyException;
import woo.core.StoreManager;
import woo.core.Cliente;
import woo.core.exception.InvalidClientKeyException;

import java.io.Serializable;

/**
 * Show client.
 */
public class DoShowClient extends Command<StoreManager> implements Serializable {

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
      _display.clear();
      int i = cl.getNrNotificacoes();
      while(i > 0){
        _display.addLine(cl.toStringNotificacoes());
        i--;
      }
      cl.resetNrNotificacoes();
      _display.display();
    } catch (InvalidClientKeyException e) {
      throw new UnknownClientKeyException(e.getMessage());
    }
  }
}
