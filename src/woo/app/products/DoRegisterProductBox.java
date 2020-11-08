package woo.app.products;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;
import woo.app.exception.DuplicateProductKeyException;
import woo.core.StoreManager;
import woo.core.exception.ProductKeyDuplicatedException;
//FIXME import other classes

/**
 * Register box.
 */
public class DoRegisterProductBox extends Command<StoreManager> {

  private Input<String> _idCaixa;
  private Input<Integer> _preco;
  private Input<Integer> _valorCritico;
  private Input<String> _idFornecedorCaixa;
  private Input<String> _tipoTransporte;
  //FIXME add input fields

  public DoRegisterProductBox(StoreManager receiver) {
    super(Label.REGISTER_BOX, receiver);
    _idCaixa = _form.addStringInput(Message.requestProductKey());
    _preco = _form.addIntegerInput(Message.requestPrice());
    _valorCritico = _form.addIntegerInput(Message.requestStockCriticalValue());
    _idFornecedorCaixa = _form.addStringInput(Message.requestSupplierKey());
    _tipoTransporte = _form.addStringInput(Message.requestServiceType());
    //FIXME init input fields
  }

  @Override
  public final void execute() throws DialogException {
    _form.parse();
    try{
      _receiver.registarCaixa(_idCaixa, _preco, _valorCritico, _idFornecedorCaixa, _tipoTransporte);
    }catch (ProductKeyDuplicatedException e){
      throw new DuplicateProductKeyException(e.getMessage());
    }
    //FIXME implement command
  }
}
