package woo.app.products;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.Input;
import woo.app.exception.DuplicateProductKeyException;
import woo.app.exception.UnknownServiceLevelException;
import woo.app.exception.UnknownServiceTypeException;
import woo.app.exception.UnknownSupplierKeyException;
import woo.core.StoreManager;
import woo.core.exception.ProductKeyDuplicatedException;
import woo.core.exception.ServiceLevelUnknownException;
import woo.core.exception.ServiceTypeUnknownException;
import woo.core.exception.SupplierUnknownException;

/**
 * Register container.
 */
public class DoRegisterProductContainer extends Command<StoreManager> {

  private Input<String> _idContentor;
  private Input<Integer> _preco;
  private Input<Integer> _valorCritico;
  private Input<String> _idFornecedorContentor;
  private Input<String> _tipoTransporte;
  private Input<String> _qualidadeServico;

  public DoRegisterProductContainer(StoreManager receiver) {
    super(Label.REGISTER_CONTAINER, receiver);
    _idContentor = _form.addStringInput(Message.requestProductKey());
    _preco = _form.addIntegerInput(Message.requestPrice());
    _valorCritico = _form.addIntegerInput(Message.requestStockCriticalValue());
    _idFornecedorContentor = _form.addStringInput(Message.requestSupplierKey());
    _tipoTransporte = _form.addStringInput(Message.requestServiceType());
    _qualidadeServico = _form.addStringInput(Message.requestServiceLevel());
  }

  @Override
  public final void execute() throws DuplicateProductKeyException, UnknownServiceTypeException, UnknownServiceLevelException,
          UnknownSupplierKeyException{
    _form.parse();
    try{
      _receiver.registarContentor(_idContentor.value(), _preco.value(), _valorCritico.value(),
              _idFornecedorContentor.value(), _tipoTransporte.value(), _qualidadeServico.value());
    }
    catch (ProductKeyDuplicatedException e){
      throw new DuplicateProductKeyException(e.getMessage());
    } catch (ServiceTypeUnknownException e){
      throw new UnknownServiceTypeException(_tipoTransporte.value());
    } catch (ServiceLevelUnknownException e){
      throw new UnknownServiceLevelException(_qualidadeServico.value());
    } catch(SupplierUnknownException e){
      throw new UnknownSupplierKeyException(_idFornecedorContentor.value());
    }
  }
}
