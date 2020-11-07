package woo.core;

import woo.core.Cliente;
import woo.core.Fornecedor;
import woo.core.Produto;

import java.util.Map;
import java.util.HashMap;

//FIXME import classes (cannot import from pt.tecnico or woo.app)
import java.io.Serializable;

import java.io.IOException;

import woo.core.exception.BadEntryException;

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
