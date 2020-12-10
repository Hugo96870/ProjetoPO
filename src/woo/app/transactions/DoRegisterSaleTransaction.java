package woo.app.transactions;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.Input;
import woo.app.exception.UnavailableProductException;
import woo.app.exception.UnknownClientKeyException;
import woo.app.exception.UnknownProductKeyException;
import woo.core.StoreManager;
import woo.core.*;
import woo.core.exception.InvalidClientKeyException;
import woo.core.exception.ProductKeyUnknownException;
import woo.core.exception.ProductUnavailableException;

/**
 * Register sale.
 */
public class DoRegisterSaleTransaction extends Command<StoreManager> {

  private Input<String> _idCliente;
  private Input<Integer> _dataLimite;
  private Input<String> _idProduto;
  private Input<Integer> _quantidade;

  public DoRegisterSaleTransaction(StoreManager receiver) {
    super(Label.REGISTER_SALE_TRANSACTION, receiver);
    _idCliente = _form.addStringInput(Message.requestClientKey());
    _dataLimite = _form.addIntegerInput(Message.requestPaymentDeadline());
    _idProduto = _form.addStringInput(Message.requestProductKey());
    _quantidade = _form.addIntegerInput(Message.requestAmount());
  }

  @Override
  public final void execute() throws UnavailableProductException, UnknownClientKeyException, UnknownProductKeyException {
    _form.parse();
    try{
      Produto p = _receiver.getProduto(_idProduto.value());
      Cliente c = _receiver.getCliente(_idCliente.value());
      _receiver.registarVenda(c, _dataLimite.value(), p, _quantidade.value());
      _receiver.adicionarSaldoContabilistico(p.getPreco()*_quantidade.value());
    }catch (InvalidClientKeyException e){
      throw new UnknownClientKeyException(e.getMessage());
    }catch (ProductUnavailableException e){
      try {
        throw new UnavailableProductException(_idProduto.value(), _quantidade.value(), _receiver.getProduto(_idProduto.value()).getQuantidade());
      } catch (ProductKeyUnknownException ex) {
        throw new UnknownProductKeyException(_idProduto.value());
      }
    }catch (ProductKeyUnknownException e){
      throw new UnknownProductKeyException(_idProduto.value());
    }
  }
}
