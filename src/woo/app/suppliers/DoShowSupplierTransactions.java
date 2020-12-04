package woo.app.suppliers;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.Input;
import woo.app.exception.UnknownClientKeyException;
import woo.app.exception.UnknownProductKeyException;
import woo.app.exception.UnknownSupplierKeyException;
import woo.core.*;
import woo.core.Fornecedor;
import woo.core.StoreManager;
import woo.core.exception.ProductKeyUnknownException;
import woo.core.exception.SupplierUnknownException;

import java.util.List;
//FIXME import other classes

/**
 * Show all transactions for specific supplier.
 */
public class DoShowSupplierTransactions extends Command<StoreManager> {

  private Input<String> _id;

  public DoShowSupplierTransactions(StoreManager receiver){
    super(Label.SHOW_SUPPLIER_TRANSACTIONS, receiver);
    _id = _form.addStringInput(Message.requestSupplierKey());
  }

  @Override
  public void execute() throws UnknownSupplierKeyException, UnknownProductKeyException{
    _form.parse();
    int i = 0;
    try{
      Fornecedor f = _receiver.getStore().getFornecedor(_id.value());
      List<Encomenda> _transacoes = f.getTransacoes();
      for(Encomenda e: _transacoes) {
        _display.popup(e.toStringEncomenda());
        _display.clear();
        List<String> produtos = e.getProdutos();
        for (String s: produtos){
          try {
            Produto p = _receiver.getProduto(s);
            int quantidade = e.buscarQuantidade(i);
            _display.popup(p.toStringProdutoTransacao(quantidade));
            _display.clear();
            i++;
          } catch (ProductKeyUnknownException ex){
            throw new UnknownProductKeyException(s);
          }
        }
      }
    } catch(SupplierUnknownException e){
      throw new UnknownSupplierKeyException(_id.value());
    }
  }
}
