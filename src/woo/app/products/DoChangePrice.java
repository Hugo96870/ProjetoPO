package woo.app.products;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;
import woo.core.StoreManager;
//FIXME import other classes

/**
 * Change product price.
 */
public class DoChangePrice extends Command<StoreManager> {
  private Input<String> _idProduto;
  private Input<Integer> _preco;

  public DoChangePrice(StoreManager receiver) {
    super(Label.CHANGE_PRICE, receiver);
    _preco = _form.addIntegerInput(Message.requestPrice());
    _idProduto = _form.addStringInput(Message.requestProductKey());
  }

  @Override
  public final void execute(){
    _form.parse();
    if(_preco.value() > 0)
      _receiver.mudarPreco(_preco.value(), _idProduto.value());
  }
}
