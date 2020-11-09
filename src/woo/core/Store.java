package woo.core;

import woo.app.exception.*;
import woo.core.Cliente;
import woo.core.Fornecedor;
import woo.core.Produto;

import java.security.Provider;
import java.util.*;
import java.util.HashMap;
import java.util.Collection;
import java.util.Set;
import java.util.TreeSet;
import woo.core.MyParser;


//FIXME import classes (cannot import from pt.tecnico or woo.app)
import java.io.Serializable;

import java.io.IOException;
import java.util.TreeSet;

import woo.core.exception.*;

/**
 * Class Store implements a store.
 */
public class Store implements Serializable {

  /** Serial number for serialization. */
  private static final long serialVersionUID = 202009192006L;

  // FIXME define attributes
    private String _filename;
    private int _data;
    private Map<String, Cliente> _clientes;
    private Map<String, Produto> _produtos;
    private Map<String, Fornecedor> _fornecedores;
  // FIXME define contructor(s)

  public Store(){
    _clientes = new TreeMap<>();
    _produtos = new TreeMap<>();
    _fornecedores = new TreeMap<>();
    _filename = null;
    _data = 0;
  }
  public String getFileName(){
    return _filename;
  }

  public Collection<Cliente> getTodosClientes(){
    return _clientes.values();
  }

  public Collection<Fornecedor> getTodosFornecedores(){
    return _fornecedores.values();
  }

  public Collection<Produto> getTodosProdutos(){
    return _produtos.values();
  }

  public void registarCliente(String id, String nome, String morada) throws ClientKeyDuplicatedException{
    if(_clientes.containsKey(id)) {
      throw new ClientKeyDuplicatedException(id);
    }
    else{
      Cliente cl = new Cliente(id, nome, morada);
      _clientes.put(id, cl);
    }
  }

  public void registarFornecedor(String id, String nome, String morada) throws SupplierKeyDuplicatedException {
    if (_fornecedores.containsKey(id)) {
      throw new SupplierKeyDuplicatedException(id);
    }
    else {
      Fornecedor fr = new Fornecedor(id, nome, morada);
      _fornecedores.put(id, fr);
    }
  }

  public void registarLivro(String id, String autor, String titulo, String ISBN, int preco, int valorCritico, String idFornecedor,
                            int quantidade) throws ProductKeyDuplicatedException{
    if(_produtos.containsKey(id)){
      throw new ProductKeyDuplicatedException(id);
    }
    else{
      Produto pr = new Livro(id, preco, valorCritico, idFornecedor, autor, ISBN, titulo, quantidade);
      _produtos.put(id,pr);
    }
  }

  public void registarContentor(String id, int preco, int valorCritico, String idFornecedor, String tipoTransporte,
                                String nivelServico, int quantidade) throws ProductKeyDuplicatedException, ServiceTypeUnknownException,
                                ServiceLevelUnknownException{
    if(_produtos.containsKey(id)){
      throw new ProductKeyDuplicatedException(id);
    }
    else{
      Produto pr = new Contentor(id, preco, valorCritico, idFornecedor, tipoTransporte, nivelServico, quantidade);
      _produtos.put(id,pr);
    }
  }

  public void registarCaixa(String id, int preco, int valorCritico, String idFornecedor, String tipoTransporte, int quantidade)
          throws ProductKeyDuplicatedException, ServiceTypeUnknownException{
    if(_produtos.containsKey(id)){
      throw new ProductKeyDuplicatedException(id);
    }
    else{
      Produto pr = new Caixa(id, preco, valorCritico, idFornecedor, tipoTransporte, quantidade);
      _produtos.put(id,pr);
    }
  }

  public int getData(){
    return _data;
  }

  public Cliente getCliente(String id) throws InvalidClientKeyException{
    if(!(_clientes.containsKey(id))){
      throw new InvalidClientKeyException(id);
    }
    return _clientes.get(id);
  }

  public void avancarData(int dias) throws InvalidDateToAdvanceException {
    if(dias < 0){
      throw new InvalidDateToAdvanceException(dias);
    }
    _data += dias;
  }
  // FIXME define methods

  /**
   * @param txtfile filename to be loaded.
   * @throws IOException
   * @throws BadEntryException
   */

  public void importFile(String txtfile) throws IOException, BadEntryException, DuplicateSupplierKeyException, DuplicateClientKeyException,
          DuplicateProductKeyException, UnknownServiceTypeException, UnknownServiceLevelException {
    //FIXME implement method
    MyParser parse = new MyParser(this);
    parse.parseFile(txtfile);
  }

}
