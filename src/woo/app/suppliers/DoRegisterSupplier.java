package woo.app.suppliers;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;
import woo.core.StoreManager;
import woo.app.exception.DuplicateSupplierKeyException;
import woo.core.exception.SupplierKeyDuplicatedException;

//FIXME import other classes

/**
 * Register supplier.
 */
public class DoRegisterSupplier extends Command<StoreManager> {
  private Input<String> _idFornecedor;
  private Input<String> _nome;
  private Input<String> _morada;
  //FIXME add input fields

  public DoRegisterSupplier(StoreManager receiver) {
    super(Label.REGISTER_SUPPLIER, receiver);
    _idFornecedor = _form.addStringInput(Message.requestSupplierKey());
    _nome = _form.addStringInput(Message.requestSupplierName());
    _morada = _form.addStringInput(Message.requestSupplierAddress());
    //FIXME init input fields
  }

  @Override
  public void execute() throws DuplicateSupplierKeyException {
    _form.parse();
    try {
      _receiver.registarFornecedor(_idFornecedor.value(), _nome.value(), _morada.value());
    }
    catch (SupplierKeyDuplicatedException e){
      throw new DuplicateSupplierKeyException(e.getMessage());
    }
    //FIXME implement command
  }
}
