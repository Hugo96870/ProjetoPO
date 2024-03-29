package woo.app.products;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.Input;
import woo.app.exception.DuplicateProductKeyException;
import woo.app.exception.UnknownSupplierKeyException;
import woo.core.StoreManager;
import woo.core.exception.ProductKeyDuplicatedException;
import woo.core.exception.SupplierUnknownException;

/**
 * Register book.
 */
public class DoRegisterProductBook extends Command<StoreManager> {

  private Input<String> _idLivro;
  private Input<String> _autor;
  private Input<String> _titulo;
  private Input<String> _ISBN;
  private Input<Integer> _preco;
  private Input<Integer> _valorCritico;
  private Input<String>_idFornecedorLivro;


  public DoRegisterProductBook(StoreManager receiver) {
    super(Label.REGISTER_BOOK, receiver);
    _idLivro = _form.addStringInput(Message.requestProductKey());
    _titulo = _form.addStringInput(Message.requestBookTitle());
    _autor = _form.addStringInput(Message.requestBookAuthor());
    _ISBN = _form.addStringInput(Message.requestISBN());
    _preco = _form.addIntegerInput(Message.requestPrice());
    _valorCritico = _form.addIntegerInput(Message.requestStockCriticalValue());
    _idFornecedorLivro = _form.addStringInput(Message.requestSupplierKey());
  }

  @Override
  public final void execute() throws DuplicateProductKeyException, UnknownSupplierKeyException {
    _form.parse();
    try{
      _receiver.registarLivro(_idLivro.value(), _autor.value(), _titulo.value(), _ISBN.value(),
              _preco.value(), _valorCritico.value(), _idFornecedorLivro.value());
    }catch (ProductKeyDuplicatedException e){
      throw new DuplicateProductKeyException(e.getMessage());
    }catch(SupplierUnknownException e){
      throw new UnknownSupplierKeyException(_idFornecedorLivro.value());
    }
  }
}
