package woo.app.clients;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;
import woo.app.exception.UnknownClientKeyException;
import woo.app.exception.UnknownProductKeyException;
import woo.core.StoreManager;
import woo.core.exception.InvalidClientKeyException;
import woo.core.*;
import java.util.HashMap;
import woo.core.exception.ProductKeyUnknownException;

import java.util.List;

/**
 * Toggle product-related notifications.
 */
public class DoToggleProductNotifications extends Command<StoreManager>{

  private Input<String> _idCliente;
  private Input<String>  _idProduto;

  public DoToggleProductNotifications(StoreManager storefront) {
    super(Label.TOGGLE_PRODUCT_NOTIFICATIONS, storefront);
    _idCliente = _form.addStringInput(Message.requestClientKey());
    _idProduto = _form.addStringInput(Message.requestProductKey());
  }

  @Override
  public void execute() throws UnknownClientKeyException, UnknownProductKeyException{
    _form.parse();
    try {
      Cliente cl = _receiver.getCliente(_idCliente.value());
      Produto p = _receiver.getProduto(_idProduto.value());
      HashMap<String, List<Observer>> _observers = _receiver.getObservers();
      for(String id: _observers.keySet()){
        if(id.equals(_idProduto.value())){
          if(_observers.get(id).contains(cl))
            _observers.get(id).remove(cl);
          else
            _observers.get(id).add((Observer)cl);
        }
      }
    } catch (InvalidClientKeyException e){
      throw new UnknownClientKeyException(_idCliente.value());
    } catch (ProductKeyUnknownException ex){
      throw new UnknownProductKeyException(_idProduto.value());
    }
  }

}
