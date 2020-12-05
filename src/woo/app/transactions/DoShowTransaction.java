package woo.app.transactions;

import pt.tecnico.po.ui.Command;
import java.util.List;
import pt.tecnico.po.ui.Input;
import woo.app.exception.UnknownClientKeyException;
import woo.app.exception.UnknownProductKeyException;
import woo.app.exception.UnknownTransactionKeyException;
import woo.core.StoreManager;
import woo.core.*;
import woo.core.exception.InvalidClientKeyException;
import woo.core.exception.ProductKeyUnknownException;

import java.util.Collection;


/**
 * Show specific transaction.
 */
public class DoShowTransaction extends Command<StoreManager> {

  private Input<Integer> _idTransacao;

  public DoShowTransaction(StoreManager receiver) {
    super(Label.SHOW_TRANSACTION, receiver);
  }

  @Override
  public final void execute() throws UnknownProductKeyException, UnknownClientKeyException, UnknownTransactionKeyException {
    _form.clear();
    _idTransacao = _form.addIntegerInput(Message.requestTransactionKey());
    _form.parse();
    Collection<Venda> _vendas = _receiver.getVendas();
    Collection<Encomenda> _encomendas = _receiver.getEncomendas();
    Transacao t = null;
    int i = 0;

    for(Venda v : _vendas){
      if(v.getID() == _idTransacao.value()){
        t = v;
        if("NÃO".equals(v.getEstado())) {
          try {
            v.setValorFinal(_receiver.atualizarCusto(v));
          } catch (InvalidClientKeyException es) {
            throw new UnknownClientKeyException(v.getIDCliente());
          }
        }
        _display.popup(v.toStringVenda());
        break;
      }
    }

    for(Encomenda e : _encomendas){
      if(e.getID() == _idTransacao.value()){
        t = e;
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
        break;
      }
    }

    if (t == null)
      throw new UnknownTransactionKeyException(_idTransacao.value());

  }

}
