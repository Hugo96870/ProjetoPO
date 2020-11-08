package woo.app.products;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;
import woo.app.exception.DuplicateProductKeyException;
import woo.app.exception.UnknownServiceLevelException;
import woo.app.exception.UnknownServiceTypeException;
import woo.core.StoreManager;
import woo.core.exception.ProductKeyDuplicatedException;
import woo.core.exception.ServiceTypeUnknownException;
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
  public final void execute() throws DuplicateProductKeyException, UnknownServiceTypeException{
    _form.parse();
    try{
      _receiver.registarCaixa(_idCaixa.value(), _preco.value(), _valorCritico.value(), _idFornecedorCaixa.value(),
                              _tipoTransporte.value());
    }catch (ProductKeyDuplicatedException e){
      throw new DuplicateProductKeyException(e.getMessage());
    }catch(ServiceTypeUnknownException e){
      throw new UnknownServiceTypeException(_tipoTransporte.value());
    }
    //FIXME implement command
  }
}
