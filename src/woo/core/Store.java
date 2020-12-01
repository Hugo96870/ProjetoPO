package woo.core;

import woo.app.exception.*;
import java.util.*;

import java.io.Serializable;
import java.io.IOException;
import woo.core.exception.*;

/**
 * Class Store implements a store.
 */
public class Store implements Serializable {

  /** Serial number for serialization. */
  private static final long serialVersionUID = 202009192006L;

    private double _saldoContabilistico;
    private double _saldoDisponivel;
    private String _filename;
    private int _data = 0;
    private Map<String, Cliente> _clientes;
    private Map<String, Produto> _produtos;
    private Map<String, Fornecedor> _fornecedores;

  public Store(){
    _clientes = new TreeMap<>();
    _produtos = new TreeMap<>();
    _fornecedores = new TreeMap<>();
    _filename = "";
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
    if(_clientes.containsKey(id) || _clientes.containsKey(id.toLowerCase()) || _clientes.containsKey(id.toUpperCase())) {
      throw new ClientKeyDuplicatedException(id);
    }
    else{
      Cliente cl = new Cliente(id, nome, morada);
      _clientes.put(id.toUpperCase(), cl);
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
      Fornecedor f = getFornecedor(idFornecedor);
      f.adicionarProduto(pr.getId());
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
      Fornecedor f = getFornecedor(idFornecedor);
      f.adicionarProduto(pr.getId());
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
      Fornecedor f = getFornecedor(idFornecedor);
      f.adicionarProduto(pr.getId());
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

  public void mudarPreco(int preco, String id){
    for (Produto p: _produtos.values()){
      if(id.equals(p.getId())){
        p.mudarPreco(preco);
      }
    }
  }

  public double getSaldoContabilistico(){
    return _saldoContabilistico;
  }

  public double getSaldoDisponivel(){
    return _saldoDisponivel;
  }

  public Produto getProduto(String id){
    for(Produto p: _produtos.values()){
      if(id.equals(p.getId()))
        return p;
    }
    return null;
  }

  public void registarVenda(Cliente c, int dataLimite, Produto p, int quantidade, int dia) throws ProductUnavailableException{
    if (quantidade > p.getQuantidade()){
      throw new ProductUnavailableException(p.getId());
    }
    Venda v = new Venda(p, quantidade, dataLimite, dia);
    c.adicionarTransacao(v);
  }

  public void registarEncomenda(List<String> produtos, List<Integer> quantidades, String idFornecedor, int custo)
                                throws SupplierUnauthorizedException, SupplierWrongException{
    Fornecedor fornecedor = null;
    for(Fornecedor f: _fornecedores.values()){
      if(idFornecedor.equals(f.getId()))
        fornecedor = f;
    }
    if(fornecedor == null){
      throw new NullPointerException();
    }
    if(!(fornecedor.getEstado()))
      throw new SupplierUnauthorizedException(fornecedor.getId());

    List<String> idProdutos = fornecedor.getProdutos();

    for(String p : produtos){
      if(!(idProdutos.contains(p)))
        throw new SupplierWrongException(idFornecedor, p);
    }

    Encomenda e = new Encomenda(produtos, fornecedor, quantidades, custo);
    fornecedor.adicionarTransacao(e);
  }

  public void adicionarSaldo(int valor){
    _saldoContabilistico += valor;
    _saldoDisponivel += valor;
  }

  public void adicionarSaldoContabilistico(int valor){
    _saldoContabilistico += valor;
  }

  public Fornecedor getFornecedor(String id){
    for (Fornecedor f: _fornecedores.values()){
      if(id.equals(f.getId()))
        return f;
    }
    return null;
  }

  /**
   * @param txtfile filename to be loaded.
   * @throws IOException
   * @throws BadEntryException
   */

  public void importFile(String txtfile) throws IOException, BadEntryException, DuplicateSupplierKeyException,
          DuplicateClientKeyException, DuplicateProductKeyException, UnknownServiceTypeException,
          UnknownServiceLevelException {
    MyParser parse = new MyParser(this);
    parse.parseFile(txtfile);
  }

}
