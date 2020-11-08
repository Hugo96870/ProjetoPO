package woo.core;

import woo.app.exception.DuplicateClientKeyException;
import woo.app.exception.DuplicateSupplierKeyException;
import woo.core.Cliente;
import woo.core.Fornecedor;
import woo.core.Produto;

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
    private int _data;
    private Map<String, Cliente> _clientes;
    private Map<String, Produto> _produtos;
    private Map<String, Fornecedor> _fornecedores;
  // FIXME define contructor(s)

  public Store(){
    _clientes = new TreeMap<>();
    _produtos = new TreeMap<>();
    _fornecedores = new TreeMap<>();
    _data = 0;
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

  public void importFile(String txtfile) throws IOException, BadEntryException, DuplicateSupplierKeyException, DuplicateClientKeyException {
    //FIXME implement method
    MyParser parse = new MyParser(this);
    parse.parseFile(txtfile);
  }

}
