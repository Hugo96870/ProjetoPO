package woo.core;

import java.io.*;
import java.util.Collection;

import woo.app.exception.*;
import woo.core.exception.*;

/**
 * StoreManager: façade for the core classes.
 */
public class StoreManager implements Serializable{

  /** Current filename. */
  private String _filename = "";

  /** The actual store. */
  private Store _store;

  public StoreManager(){
      _store = new Store();
  }

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

  public void registarLivro(String id, String autor, String titulo, String ISBN, int preco, int valorCritico,
                            String idFornecedor) throws ProductKeyDuplicatedException{
    _store.registarLivro(id, autor, titulo, ISBN, preco, valorCritico, idFornecedor, 0);
  }

  public void registarContentor(String id, int preco, int valorCritico, String idFornecedor, String tipoTransporte,
                                String nivelServico) throws ProductKeyDuplicatedException, ServiceLevelUnknownException,
                                ServiceTypeUnknownException{
    _store.registarContentor(id, preco, valorCritico, idFornecedor, tipoTransporte, nivelServico, 0);
  }

  public void registarCaixa(String id, int preco, int valorCritico, String idFornecedor, String tipoTransporte)
          throws ProductKeyDuplicatedException, ServiceTypeUnknownException{
    _store.registarCaixa(id, preco, valorCritico, idFornecedor, tipoTransporte, 0);
  }

  /**
   * @throws IOException
   * @throws FileNotFoundException
   * @throws MissingFileAssociationException
   */
  public void save() throws IOException, FileNotFoundException{
    ObjectOutputStream obOut = null;
    if (_filename.equals("")){
      throw new FileNotFoundException();
    }
    try{
      FileOutputStream fpout = new FileOutputStream(_filename);
      obOut = new ObjectOutputStream(fpout);
      obOut.writeObject(_store);
      } finally{
        if (obOut!=null){
          obOut.close();
      }
    }
  }

  /**
   * @param filename
   * @throws MissingFileAssociationException
   * @throws IOException
   * @throws FileNotFoundException
   */
  public void saveAs(String filename) throws MissingFileAssociationException{
    _filename=filename;
    try {
      save();
    } catch(FileNotFoundException e){
      throw new MissingFileAssociationException();
    } catch(IOException e){
      throw new MissingFileAssociationException();
    }
  }

  /**
   * @param filename
   * @throws UnavailableFileException
   */
  public void load(String filename) throws IOException, UnavailableFileException {
    ObjectInputStream obIN = null;
    try {
      FileInputStream fpin = new FileInputStream(filename);
      obIN = new ObjectInputStream(fpin);
      Object obj = obIN.readObject();
      _store = (Store)obj;
    } catch(ClassNotFoundException | IOException e){
      throw new UnavailableFileException(filename);
    } finally{
      if (obIN != null){
        obIN.close();
      }
    }
  }

  /**
   * @param textfile
   * @throws ImportFileException
   */
  public void importFile(String textfile) throws ImportFileException, DuplicateClientKeyException,
          DuplicateSupplierKeyException, DuplicateProductKeyException, UnknownServiceLevelException,
          UnknownServiceTypeException {
    try {
      _store.importFile(textfile);
    } catch (IOException  e) {
      throw new ImportFileException(textfile);
    } catch (BadEntryException e){
      throw new ImportFileException(textfile);
    }
  }
}

