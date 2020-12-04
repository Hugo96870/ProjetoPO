package woo.app.transactions;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;
import woo.app.exception.UnknownTransactionKeyException;
import woo.core.StoreManager;
import woo.core.*;

import java.util.Collection;


/**
 * Show specific transaction.
 */
public class DoShowTransaction extends Command<StoreManager> {

  private Input<Integer> _idTransacao;

  public DoShowTransaction(StoreManager receiver) {
    super(Label.SHOW_TRANSACTION, receiver);
    _idTransacao = _form.addIntegerInput(Message.requestTransactionKey());
  }

  @Override
  public final void execute() throws DialogException {
    _form.parse();
    Collection<Venda> _vendas = _receiver.getVendas();
    Collection<Encomenda> _encomendas = _receiver.getEncomendas();
    Transacao t = null;

    for(Venda v : _vendas){
      if(v.getID() == _idTransacao.value()){
        t = v;
        _display.popup(v.toStringVenda());
        break;
      }
    }

    for(Encomenda e : _encomendas){
      if(e.getID() == _idTransacao.value()){
        t = e;
        _display.popup(e.toStringEncomenda());
        break;
      }
    }

    if (t == null)
      throw new UnknownTransactionKeyException(_idTransacao.value());

  }

}
