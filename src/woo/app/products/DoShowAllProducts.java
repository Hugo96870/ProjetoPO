package woo.app.products;

import pt.tecnico.po.ui.Command;
import woo.core.StoreManager;
import woo.core.Produto;
import java.util.Collection;

/**
 * Show all products.
 */
public class DoShowAllProducts extends Command<StoreManager> {

  public DoShowAllProducts(StoreManager receiver) {
    super(Label.SHOW_ALL_PRODUCTS, receiver);
  }

  @Override
  public final void execute(){
    Collection<Produto> produtos = _receiver.getTodosProdutos();
    for(Produto pr : produtos){
      _display.addLine(pr.toStringProduto());
    }
    _display.display();
  }

}
