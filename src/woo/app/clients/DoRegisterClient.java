package woo.app.clients;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;
import woo.app.exception.DuplicateClientKeyException;
import woo.core.StoreManager;
import woo.core.exception.ClientKeyDuplicatedException;
//FIXME import other classes

/**
 * Register new client.
 */
public class DoRegisterClient extends Command<StoreManager> {
  private Input<String> _nome;
  private Input<String> _idCliente;
  private Input<String> _morada;
  //FIXME add input fields

  public DoRegisterClient(StoreManager storefront) {
    super(Label.REGISTER_CLIENT, storefront);
    _idCliente = _form.addStringInput(Message.requestClientKey());
    _nome = _form.addStringInput(Message.requestClientName());
    _morada = _form.addStringInput(Message.requestClientAddress());
    //FIXME init input fields
  }

  @Override
  public void execute() throws DuplicateClientKeyException {
    _form.parse();
    try{
      _receiver.registarCliente(_idCliente.value(), _nome.value(), _morada.value());
    } catch(ClientKeyDuplicatedException e){
      throw new DuplicateClientKeyException(e.getMessage());
    }
    //FIXME implement command
  }

}
