package woo.app.products;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;
import woo.core.StoreManager;
//FIXME import other classes

/**
 * Register container.
 */
public class DoRegisterProductContainer extends Command<StoreManager> {

  private Input<String> _idCaixa;
  private Input<Integer> _preco;
  private Input<Integer> _valorCritico;
  private Input<String> _idFornecedorCaixa;
  private Input<String> _tipoTransporte;
  private Input<String> _qualidadeServico;
  //FIXME add input fields

  public DoRegisterProductContainer(StoreManager receiver) {
    super(Label.REGISTER_CONTAINER, receiver);
    _idCaixa = _form.addStringInput(Message.requestProductKey());
    _preco = _form.addIntegerInput(Message.requestPrice());
    _valorCritico = _form.addIntegerInput(Message.requestStockCriticalValue());
    _idFornecedorCaixa = _form.addStringInput(Message.requestSupplierKey());
    _tipoTransporte = _form.addStringInput(Message.requestServiceType());
    _qualidadeServico = _form.addStringInput(Message.requestServiceLevel());
    //FIXME init input fields
  }

  @Override
  public final void execute() throws DialogException {
    //FIXME implement command
  }
}
