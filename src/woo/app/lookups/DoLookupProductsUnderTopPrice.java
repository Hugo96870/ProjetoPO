package woo.app.lookups;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;
import woo.app.products.DoShowAllProducts;
import woo.core.Produto;
import woo.core.StoreManager;

import java.util.Collection;
import java.util.List;
import java.util.LinkedList;


/**
 * Lookup products cheaper than a given price.
 */
public class DoLookupProductsUnderTopPrice extends Command<StoreManager> {

  private Input<Integer> _precoLimite;

  public DoLookupProductsUnderTopPrice(StoreManager storefront) {
    super(Label.PRODUCTS_UNDER_PRICE, storefront);
    _precoLimite = _form.addIntegerInput(Message.requestPriceLimit());
  }

  @Override
  public void execute(){
    _form.parse();
    Collection<Produto> produtos = _receiver.getTodosProdutos();
    List<Produto> produtosLimite = new LinkedList<>();
    for(Produto p: produtos) {
      if (p.getPreco() < _precoLimite.value()) {
        produtosLimite.add(p);
      }
    }
    for(Produto p: produtosLimite)
      _display.addLine(p.toStringProduto());
    _display.display();
  }
}
