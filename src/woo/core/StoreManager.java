package woo.core;

import java.io.*;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

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

  /****************************************** CLIENTE **************************************************/
  public void registarCliente(String id, String nome, String morada) throws ClientKeyDuplicatedException{
    _store.registarCliente(id, nome, morada);
  }

  public Collection<Cliente> getTodosClientes(){
    return _store.getTodosClientes();
  }

  public Cliente getCliente(String id) throws InvalidClientKeyException{
    return _store.getCliente(id);
  }

  /***************************************  FORNECEDOR  ************************************************/
  public void registarFornecedor(String id, String nome, String morada) throws SupplierKeyDuplicatedException{
    _store.registarFornecedor(id, nome, morada);
  }

  public String mudarEstadoFornecedor(String id) throws SupplierUnknownException{
    return _store.mudarEstadoFornecedor(id);
  }

  public Collection<Fornecedor> getTodosFornecedores(){
    return _store.getTodosFornecedores();
  }
  /***************************************  PRODUTOS ************************************************/
  public void registarLivro(String id, String autor, String titulo, String ISBN, int preco, int valorCritico,
                            String idFornecedor) throws ProductKeyDuplicatedException, SupplierUnknownException{
    _store.registarLivro(id, autor, titulo, ISBN, preco, valorCritico, idFornecedor, 0);
  }

  public void registarCaixa(String id, int preco, int valorCritico, String idFornecedor, String tipoTransporte)
          throws ProductKeyDuplicatedException, ServiceTypeUnknownException, SupplierUnknownException{
    _store.registarCaixa(id, preco, valorCritico, idFornecedor, tipoTransporte, 0);
  }

  public void registarContentor(String id, int preco, int valorCritico, String idFornecedor, String tipoTransporte,
                                String nivelServico) throws ProductKeyDuplicatedException, ServiceLevelUnknownException,
          ServiceTypeUnknownException, SupplierUnknownException{
    _store.registarContentor(id, preco, valorCritico, idFornecedor, tipoTransporte, nivelServico, 0);
  }

  public void mudarPreco(int preco, String id){
    _store.mudarPreco(preco, id);
  }

  public Produto getProduto(String id) throws ProductKeyUnknownException{
    Produto p = _store.getProduto(id);
    return p;
  }

  public Collection<Produto> getTodosProdutos(){
    return _store.getTodosProdutos();
  }
  /***************************************  TRANSACOES  ************************************************/

  public void registarVenda(Cliente c, int dataLimite, Produto p, int quantidade) throws ProductUnavailableException{
    _store.registarVenda(c, dataLimite, p, quantidade, getData());
  }

  public void registarEncomenda(List<String> produtos, List<Integer> quantidades, String idFornecedor, int custo)
          throws SupplierUnauthorizedException, SupplierWrongException, SupplierUnknownException, UnknownProductKeyException{
    _store.registarEncomenda(produtos, quantidades, idFornecedor, custo);
  }

  public Collection<Venda> getVendas(){
    return _store.getVendas();
  }

  public Collection<Encomenda> getEncomendas(){
    return _store.getEncomendas();
  }

  public double pagar(Venda v) throws InvalidClientKeyException{
    double custo = _store.pagar(v);
    return custo;
  }

  public double atualizarCusto(Venda v) throws InvalidClientKeyException{
    return _store.atualizarCusto(v);
  }

  /***************************************  NOTIFICACOES  ************************************************/

  public HashMap<String, List<Observer>> getObservers(){
    return _store.getObservers();
  }
  /***************************************  LOJA  ************************************************/
  public Store getStore(){
    return _store;
  }

  public void setFilename(String nome){
    _filename = nome;
  }

  public void adicionarSaldo(int valor){
    _store.adicionarSaldo(valor);
  }

  public void adicionarSaldoContabilistico(int valor){
    _store.adicionarSaldoContabilistico(valor);
  }

  public double getSaldoDisponivel(){
    return getSaldoDisponivel();
  }

  public double getSaldoContabilistico(){
    return getSaldoContabilistico();
  }

  public int getData(){
    return _store.getData();
  }

  public void avancarData(int dias) throws InvalidDateToAdvanceException{
    _store.avancarData(dias);
  }

  /***************************************  GUARDAR E ABRIR  ************************************************/
  /**
   * @throws IOException
   * @throws FileNotFoundException
   * @throws MissingFileAssociationException
   */
  public void save() throws IOException, FileNotFoundException{
    ObjectOutputStream obOut = null;
    if ("".equals(_filename)){
      throw new FileNotFoundException();
    }
    try(FileOutputStream fpout = new FileOutputStream(_filename)){
      obOut = new ObjectOutputStream(fpout);
      obOut.writeObject(_store);
    }finally {
      obOut.close();
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
    try(ObjectInputStream obIN = new ObjectInputStream(new FileInputStream(filename))) {
      Object obj = obIN.readObject();
      _store = (Store)obj;
    } catch(ClassNotFoundException | IOException e){
      throw new UnavailableFileException(filename);
    }
  }

  /**
   * @param textfile
   * @throws ImportFileException
   */
  public void importFile(String textfile) throws ImportFileException, DuplicateClientKeyException,
          DuplicateSupplierKeyException, DuplicateProductKeyException, UnknownServiceLevelException,
          UnknownServiceTypeException, UnknownSupplierKeyException {
    try {
      _store.importFile(textfile);
    } catch (IOException | BadEntryException e) {
      throw new ImportFileException(textfile);
    }
  }
}

