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
    private Map<Integer, Venda> _transacoesV;
    private Map<Integer, Encomenda> _transacoesE;

  public Store(){
    _clientes = new TreeMap<>();
    _produtos = new TreeMap<>();
    _fornecedores = new TreeMap<>();
    _transacoesE = new TreeMap<>();
    _transacoesV = new TreeMap<>();
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
    if (_fornecedores.containsKey(id.toUpperCase())) {
      throw new SupplierKeyDuplicatedException(id);
    }
    else {
      Fornecedor fr = new Fornecedor(id, nome, morada);
      _fornecedores.put(id.toUpperCase(), fr);
    }
  }

  public void registarLivro(String id, String autor, String titulo, String ISBN, int preco, int valorCritico, String idFornecedor,
                            int quantidade) throws ProductKeyDuplicatedException, SupplierUnknownException{
    if(_produtos.containsKey(id.toUpperCase())){
      throw new ProductKeyDuplicatedException(id);
    }
    else{
      Produto pr = new Livro(id, preco, valorCritico, idFornecedor, autor, ISBN, titulo, quantidade);
      _produtos.put(id.toUpperCase(),pr);
      Fornecedor f = getFornecedor(idFornecedor);
      f.adicionarProduto(pr.getId());
    }
  }

  public void registarContentor(String id, int preco, int valorCritico, String idFornecedor, String tipoTransporte,
                                String nivelServico, int quantidade) throws ProductKeyDuplicatedException, ServiceTypeUnknownException,
                                ServiceLevelUnknownException, SupplierUnknownException{
    if(_produtos.containsKey(id.toUpperCase())){
      throw new ProductKeyDuplicatedException(id);
    }
    else{
      Produto pr = new Contentor(id, preco, valorCritico, idFornecedor, tipoTransporte, nivelServico, quantidade);
      _produtos.put(id.toUpperCase(),pr);
      Fornecedor f = getFornecedor(idFornecedor);
      f.adicionarProduto(pr.getId());
    }
  }

  public void registarCaixa(String id, int preco, int valorCritico, String idFornecedor, String tipoTransporte, int quantidade)
          throws ProductKeyDuplicatedException, ServiceTypeUnknownException, SupplierUnknownException{
    if(_produtos.containsKey(id.toUpperCase())){
      throw new ProductKeyDuplicatedException(id);
    }
    else{
      try {
        Fornecedor f = getFornecedor(idFornecedor);
        Produto pr = new Caixa(id, preco, valorCritico, idFornecedor, tipoTransporte, quantidade);
        _produtos.put(id.toUpperCase(), pr);
        f.adicionarProduto(pr.getId());
      } catch(SupplierUnknownException e){
        throw new SupplierUnknownException(idFornecedor);
      }
    }
  }

  public int getData(){
    return _data;
  }

  public Cliente getCliente(String id) throws InvalidClientKeyException{
    if(!(_clientes.containsKey(id.toUpperCase()))){
      throw new InvalidClientKeyException(id);
    }
    return _clientes.get(id.toUpperCase());
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

  public Produto getProduto(String id) throws ProductKeyUnknownException{
    if(!_produtos.containsKey(id.toUpperCase()))
      throw new ProductKeyUnknownException(id);
    else {
      for (Produto p : _produtos.values()) {
        if (id.toUpperCase().equals(p.getId().toUpperCase()))
          return p;
      }
    }
    return null;
  }

  public Collection<Venda> getVendas(){
    return _transacoesV.values();
  }

  public Collection<Encomenda> getEncomendas(){
    return _transacoesE.values();
  }

  public void registarVenda(Cliente c, int dataLimite, Produto p, int quantidade, int dia) throws ProductUnavailableException{
    if (quantidade > p.getQuantidade()){
      throw new ProductUnavailableException(p.getId());
    }
    int custoBase = p.getPreco()*quantidade;
    Venda v = new Venda(p, quantidade, dataLimite, dia, c.getIdCliente(), custoBase);
    p.removerQuantidade(quantidade);
    c.adicionarTransacao(v);
    c.aumentarValorComprado(custoBase);
    _transacoesV.put(v.getID(), v);
  }

  public void registarEncomenda(List<String> produtos, List<Integer> quantidades, String idFornecedor, int custo)
                                throws SupplierUnauthorizedException, SupplierWrongException, SupplierUnknownException{
    Fornecedor fornecedor = getFornecedor(idFornecedor);
    if(!(fornecedor.getEstado()))
      throw new SupplierUnauthorizedException(fornecedor.getId());

    List<String> idProdutos = fornecedor.getProdutos();

    for(String p : produtos){
      if(!(idProdutos.contains(p)))
        throw new SupplierWrongException(idFornecedor, p);
    }

    Encomenda e = new Encomenda(produtos, fornecedor, quantidades, custo, getData());
    fornecedor.adicionarTransacao(e);
    _transacoesE.put(e.getID(), e);
  }

  public void adicionarSaldo(int valor){
    _saldoContabilistico -= valor;
    _saldoDisponivel -= valor;
  }

  public double atualizarCusto(Venda v) throws InvalidClientKeyException{
    double custobase = v.getProduto().getPreco() * v.getQuantidade();
    double custoreal = custobase;
    if(v.getProduto().getTipo() == TipoDeProduto.BOOK) {
      int dias = v.getDataLimite() - getData();
      if (dias >= 3) {
        custoreal *= 0.9;
      }
      else if(dias >= 0) {
        double valor = pagamentoP2(v, custobase, dias);
        custoreal -= valor;
      }
      else if(dias >= -3){
        double valor = pagamentoP3(v, custobase, dias);
        custoreal += valor;
      }
      else {
        double valor = pagamentoP4(v, custobase, dias);
        custoreal += valor;
      }
    }
    else if(v.getProduto().getTipo() == TipoDeProduto.BOX) {
      int dias = v.getDataLimite() - getData();
      if (dias >= 5) {
        custoreal *= 0.9;
      }
      else if(dias >= 0) {
        double valor = pagamentoP2(v, custobase, dias);
        custoreal -= valor;
      }
      else if(dias >= -5) {
        double valor = pagamentoP3(v, custobase, dias);
        custoreal += valor;
      }
      else {
        double valor = pagamentoP4(v, custobase, dias);
        custoreal += valor;
      }
    }
    else if(v.getProduto().getTipo() == TipoDeProduto.CONTAINER) {
      int dias = v.getDataLimite() - getData();
      if (dias >= 8) {
        custoreal *= 0.9;
      }
      else if(dias >= 0) {
        double valor = pagamentoP2(v, custobase, dias);
        custoreal -= valor;
      }
      else if(dias >= -8){
        double valor = pagamentoP3(v, custobase, dias);
        custoreal += valor;
      }
      else {
        double valor = pagamentoP4(v, custobase, dias);
        custoreal += valor;
      }
    }
    return custoreal;
  }


  public double pagar(Venda v) throws InvalidClientKeyException{
    double custobase = v.getProduto().getPreco() * v.getQuantidade();
    double custoreal = custobase;
    if(v.getProduto().getTipo() == TipoDeProduto.BOOK) {
      int dias = v.getDataLimite() - getData();
      if (dias >= 3) {
        custoreal *= 0.9;
        getCliente(v.getIDCliente()).mudarPontos(custoreal * 10);
      }
      else if(dias >= 0) {
        double valor = pagamentoP2(v, custobase, dias);
        custoreal -= valor;
        getCliente(v.getIDCliente()).mudarPontos(custoreal * 10);
      }
      else if(dias >= -3){
        double valor = pagamentoP3(v, custobase, dias);
        custoreal += valor;
      }
      else {
        double valor = pagamentoP4(v, custobase, dias);
        custoreal += valor;
      }
    }
    else if(v.getProduto().getTipo() == TipoDeProduto.BOX) {
      int dias = v.getDataLimite() - getData();
      if (dias >= 5) {
        custoreal *= 0.9;
        getCliente(v.getIDCliente()).mudarPontos(custoreal * 10);
      }
      else if(dias >= 0) {
        double valor = pagamentoP2(v, custobase, dias);
        custoreal -= valor;
        getCliente(v.getIDCliente()).mudarPontos(custoreal * 10);
      }
      else if(dias >= -5) {
        double valor = pagamentoP3(v, custobase, dias);
        custoreal += valor;
      }
      else {
        double valor = pagamentoP4(v, custobase, dias);
        custoreal += valor;
      }
    }
    else if(v.getProduto().getTipo() == TipoDeProduto.CONTAINER) {
      int dias = v.getDataLimite() - getData();
      if (dias >= 8) {
        custoreal *= 0.9;
        getCliente(v.getIDCliente()).mudarPontos(custoreal * 10);
      }
      else if(dias >= 0) {
        double valor = pagamentoP2(v, custobase, dias);
        custoreal -= valor;
        getCliente(v.getIDCliente()).mudarPontos(custoreal * 10);
      }
      else if(dias >= -8){
        double valor = pagamentoP3(v, custobase, dias);
        custoreal += valor;
      }
      else {
        double valor = pagamentoP4(v, custobase, dias);
        custoreal += valor;
      }
    }
    return custoreal;
  }

  public double pagamentoP2(Venda v, double custoBase, int dias) throws InvalidClientKeyException{
    double valor = 0;
    if("NORMAL".equals(getCliente(v.getIDCliente()).getEstatuto()))
      valor = 0;
    else if("SELECTION".equals(getCliente(v.getIDCliente()).getEstatuto()))
      if (dias > 2)
        valor = 0.05*custoBase;
    else
      valor = 0.1*custoBase;
    return valor;
  }

  public double pagamentoP3(Venda v, double custoBase, int dias) throws InvalidClientKeyException{
    double valor = 0;
    if("NORMAL".equals(getCliente(v.getIDCliente()).getEstatuto())) {
      double multa = (-1 * dias) * 0.05;
      valor = custoBase * multa;
    }
    else if("SELECTION".equals(getCliente(v.getIDCliente()).getEstatuto())) {
      if (dias == -1)
        valor = 0;
      else {
        if(dias <= -2)
          getCliente(v.getIDCliente()).mudarPontos(-(getCliente(v.getIDCliente()).getPontos() * 0.9));
        double multa = 0.02 * (-1 * (dias + 1));
        valor = custoBase * multa;
      }
    }
    else{
      valor = -1 *custoBase*0.05;
    }
    return valor;
  }

  public double pagamentoP4(Venda v, double custoBase, int dias) throws InvalidClientKeyException{
    double valor = 0;
    if("NORMAL".equals(getCliente(v.getIDCliente()).getEstatuto())) {
      double multa = (-1 * dias) * 0.1;
      valor = custoBase * multa;
    }
    else if("SELECTION".equals(getCliente(v.getIDCliente()).getEstatuto())){
      if(dias <= -2)
        getCliente(v.getIDCliente()).mudarPontos(-(getCliente(v.getIDCliente()).getPontos() * 0.9));
      double multa = (-1 * dias) * 0.05;
      valor = custoBase * multa;
    }
    else {
      if(dias <= -15)
        getCliente(v.getIDCliente()).mudarPontos(-(getCliente(v.getIDCliente()).getPontos() * 0.75));
      valor = 0;
    }
    return valor;
  }

  public String mudarEstadoFornecedor(String id) throws SupplierUnknownException{
    String s;
    Fornecedor f = getFornecedor(id);
    if(f.getEstado() == true)
      s = f.setEstado(false);
    else
      s = f.setEstado(true);
    return s;
  }

  public void adicionarSaldoContabilistico(int valor){
    _saldoContabilistico += valor;
  }

  public Fornecedor getFornecedor(String id) throws SupplierUnknownException{
    if(!_fornecedores.containsKey(id.toUpperCase()))
      throw new SupplierUnknownException(id);
    else {
      for (Fornecedor f : _fornecedores.values()) {
        if (id.toUpperCase().equals(f.getId().toUpperCase()))
          return f;
      }
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
          UnknownServiceLevelException, UnknownSupplierKeyException {
    MyParser parse = new MyParser(this);
    parse.parseFile(txtfile);
  }

}
