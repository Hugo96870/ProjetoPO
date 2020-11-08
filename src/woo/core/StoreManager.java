package woo.core;

import java.io.IOException;
import java.io.FileNotFoundException;
import java.text.CollationElementIterator;
import java.util.Map;
import java.util.Collection;
import java.util.HashMap;

import woo.app.exception.FileOpenFailedException;
import woo.core.exception.*;

/**
 * StoreManager: façade for the core classes.
 */
public class StoreManager {

  /** Current filename. */
  private String _filename = "";

  /** The actual store. */
  private Store _store = new Store();

  //FIXME define other attributes
  //FIXME define constructor(s)
  public StoreManager(){
    
  }
  //FIXME define other methods

  public Store getStore(){
    return _store;
  }

  public Cliente getCliente(String id) throws InvalidClientKeyException{
    return _store.getCliente(id);
  }

  public int getData(){
    return _store.getData();
  }

  public void avancarData(int dias) throws InvalidDateToAdvanceException{
      _store.avancarData(dias);
  }
  public Collection<Cliente> getTodosClientes(){
    return _store.getTodosClientes();
  }

  public Collection<Fornecedor> getTodosFornecedores(){
    return _store.getTodosFornecedores();
  }

  public Collection<Produto> getTodosProdutos(){
    return _store.getTodosProdutos();
  }

  public void registarCliente(String id, String nome, String morada) throws ClientKeyDuplicatedException{
    _store.registarCliente(id, nome, morada);
  }

  public void registarFornecedor(String id, String nome, String morada) throws SupplierKeyDuplicatedException{
    _store.registarFornecedor(id, nome, morada);
  }


  /**
   * @throws IOException
   * @throws FileNotFoundException
   * @throws MissingFileAssociationException
   */
  public void save() throws IOException, FileNotFoundException, MissingFileAssociationException {
    //FIXME implement serialization method
  }

  /**
   * @param filename
   * @throws MissingFileAssociationException
   * @throws IOException
   * @throws FileNotFoundException
   */
  public void saveAs(String filename) throws MissingFileAssociationException, FileNotFoundException, IOException {
    _filename = filename;
    save();
  }

  /**
   * @param filename
   * @throws UnavailableFileException
   */
  public void load(String filename) throws UnavailableFileException {
    //FIXME implement serialization method

  }

  /**
   * @param textfile
   * @throws ImportFileException
   */
  public void importFile(String textfile) throws ImportFileException{
    try {
      _store.importFile(textfile);
    } catch (IOException | BadEntryException e) {
      throw new ImportFileException(textfile);
    }
  }

}
