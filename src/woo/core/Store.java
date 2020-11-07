package woo.core;

import woo.core.Cliente;
import woo.core.Fornecedor;
import woo.core.Produto;

import java.util.Map;
import java.util.HashMap;
import java.util.Collection;

//FIXME import classes (cannot import from pt.tecnico or woo.app)
import java.io.Serializable;

import java.io.IOException;

import woo.core.exception.BadEntryException;
import woo.core.exception.ClientKeyDuplicatedException;
import woo.core.exception.InvalidClientKeyException;
import woo.core.exception.InvalidDateToAdvanceException;

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
    _clientes = new HashMap<>();
    _produtos = new HashMap<>();
    _fornecedores = new HashMap<>();
    _data = 0;
  }

  public Collection<Cliente> getTodosClientes(){
    return _clientes.values();
  }

  public Collection<Produto> getTodosProdutos(){
    return _produtos.values();
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
  void importFile(String txtfile) throws IOException, BadEntryException /* FIXME maybe other exceptions */ {
    //FIXME implement method
  }

}
