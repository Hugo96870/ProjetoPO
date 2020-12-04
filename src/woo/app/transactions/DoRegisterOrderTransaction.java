package woo.app.transactions;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;
import woo.app.exception.UnauthorizedSupplierException;
import woo.app.exception.UnknownProductKeyException;
import woo.app.exception.UnknownSupplierKeyException;
import woo.app.exception.WrongSupplierException;
import woo.core.StoreManager;
import woo.core.*;
import woo.core.exception.ProductKeyUnknownException;
import woo.core.exception.SupplierUnauthorizedException;
import woo.core.exception.SupplierUnknownException;
import woo.core.exception.SupplierWrongException;

import java.lang.invoke.WrongMethodTypeException;
import java.util.List;
import java.util.LinkedList;

/**
 * Register order.
 */
public class DoRegisterOrderTransaction extends Command<StoreManager> {

  private Input<String> _idFornecedor;
  private Input<String> _idProduto;
  private Input<Integer> _quantidade;

  public DoRegisterOrderTransaction(StoreManager receiver) {
    super(Label.REGISTER_ORDER_TRANSACTION, receiver);
    _idFornecedor = _form.addStringInput(Message.requestSupplierKey());
  }

  @Override
  public final void execute() throws UnauthorizedSupplierException, WrongSupplierException, UnknownProductKeyException,
          UnknownSupplierKeyException{
    List<String> produtos = new LinkedList<>();
    List<Integer> quantidades = new LinkedList<>();
    int custoTotal = 0;
    Produto p;
    Input<String> estado;
    _form.parse();
    _form.clear();
    while(true){
      _idProduto = _form.addStringInput(Message.requestProductKey());
      _quantidade = _form.addIntegerInput(Message.requestAmount());
      estado = _form.addStringInput(Message.requestMore());
      _form.parse();
      produtos.add(_idProduto.value());
      quantidades.add(_quantidade.value());
      try {
        p = _receiver.getProduto(_idProduto.value());
      } catch (ProductKeyUnknownException e) {
        throw new UnknownProductKeyException(_idProduto.value());
      }
      p.removerQuantidade(_quantidade.value());
      custoTotal += p.getPreco()*_quantidade.value();
      if("n".equals(estado.value()))
        break;
      _form.clear();
    }
    try {
      _receiver.registarEncomenda(produtos, quantidades, _idFornecedor.value(), custoTotal);
      _receiver.adicionarSaldo(custoTotal);
    } catch(SupplierUnauthorizedException e){
      throw new UnauthorizedSupplierException(_idFornecedor.value());
    } catch(SupplierWrongException q){
      throw new WrongSupplierException(_idFornecedor.value(), q.getMessage());
    } catch (SupplierUnknownException z){
      throw new UnknownSupplierKeyException(_idFornecedor.value());
    }
  }
}