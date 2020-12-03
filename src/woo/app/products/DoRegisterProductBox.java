package woo.app.products;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.Input;
import woo.app.exception.DuplicateProductKeyException;
import woo.app.exception.UnknownServiceTypeException;
import woo.app.exception.UnknownSupplierKeyException;
import woo.core.StoreManager;
import woo.core.exception.ProductKeyDuplicatedException;
import woo.core.exception.ServiceTypeUnknownException;
import woo.core.exception.SupplierUnknownException;

/**
 * Register box.
 */
public class DoRegisterProductBox extends Command<StoreManager> {

  private Input<String> _idCaixa;
  private Input<Integer> _preco;
  private Input<Integer> _valorCritico;
  private Input<String> _idFornecedorCaixa;
  private Input<String> _tipoTransporte;

  public DoRegisterProductBox(StoreManager receiver) {
    super(Label.REGISTER_BOX, receiver);
    _idCaixa = _form.addStringInput(Message.requestProductKey());
    _preco = _form.addIntegerInput(Message.requestPrice());
    _valorCritico = _form.addIntegerInput(Message.requestStockCriticalValue());
    _idFornecedorCaixa = _form.addStringInput(Message.requestSupplierKey());
    _tipoTransporte = _form.addStringInput(Message.requestServiceType());
  }

  @Override
  public final void execute() throws DuplicateProductKeyException, UnknownServiceTypeException, UnknownSupplierKeyException{
    _form.parse();
    try{
      _receiver.registarCaixa(_idCaixa.value(), _preco.value(), _valorCritico.value(), _idFornecedorCaixa.value(),
                              _tipoTransporte.value());
    }catch (ProductKeyDuplicatedException e){
      throw new DuplicateProductKeyException(e.getMessage());
    }catch(ServiceTypeUnknownException e){
      throw new UnknownServiceTypeException(_tipoTransporte.value());
    }catch(SupplierUnknownException e){
      throw new UnknownSupplierKeyException(_idFornecedorCaixa.value());
    }
  }
}
