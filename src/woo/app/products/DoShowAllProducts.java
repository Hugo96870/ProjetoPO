package woo.app.products;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;
import woo.core.StoreManager;
import woo.core.Produto;
import java.util.Collection;
//FIXME import other classes

/**
 * Show all products.
 */
public class DoShowAllProducts extends Command<StoreManager> {

  //FIXME add input fields

  public DoShowAllProducts(StoreManager receiver) {
    super(Label.SHOW_ALL_PRODUCTS, receiver);
    //FIXME init input fields
  }

  @Override
  public final void execute(){
    Collection<Produto> produtos = _receiver.getTodosProdutos();
    for(Produto pr : produtos){
      _display.addLine(pr.toStringProduto());
    }
    _display.display();
    //FIXME implement command
  }

}
