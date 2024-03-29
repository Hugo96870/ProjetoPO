package woo.core;

import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.Serializable;

import woo.app.exception.*;
import woo.core.exception.*;
// add here more imports if needed

public class MyParser implements Serializable{
  private Store _store;  // ou outra entidade

  MyParser(Store s) {
    _store = s;
  }

  void parseFile(String fileName) throws IOException, BadEntryException, DuplicateClientKeyException, DuplicateSupplierKeyException,
          DuplicateProductKeyException, UnknownServiceLevelException, UnknownServiceTypeException, UnknownSupplierKeyException {

    try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
      String line;
      while ((line = reader.readLine()) != null) {
        parseLine(line);
      }
    } 
  }

  private void parseLine(String line) throws BadEntryException, DuplicateSupplierKeyException, DuplicateClientKeyException,
          DuplicateProductKeyException, UnknownServiceTypeException, UnknownServiceLevelException, UnknownSupplierKeyException {
    String[] components = line.split("\\|");

    switch(components[0]) {
      case "SUPPLIER":
        parseSupplier(line, components);
        break;

      case "CLIENT":
        parseClient(line, components);
        break;

      case "BOX":
        parseBox(line, components);
        break;

      case "CONTAINER":
        parseContainer(line, components);
        break;

      case "BOOK":
        parseBook(line, components);
        break;

      default:
        throw new BadEntryException("Type of line not supported: " + line);
    }
  }

  // Format: SUPPLIER|id|nome|endereço
  private void parseSupplier(String line, String[] components)  throws BadEntryException, DuplicateSupplierKeyException {
    if (components.length != 4)
      throw new BadEntryException("Invalid number of fields in supplier description (4) " + line);

    String id = components[1];
    String name = components[2];
    String address = components[3];
    try {
      _store.registarFornecedor(id, name, address);
    } catch(SupplierKeyDuplicatedException e){
      throw new DuplicateSupplierKeyException(e.getMessage());
    }
  }

  // Format: CLIENT|id|nome|endereço
  private void parseClient(String line, String[] components) throws BadEntryException, DuplicateClientKeyException {
    if (components.length != 4)
      throw new BadEntryException("Invalid number of fields (4) in client description: " + line);
    String id = components[1];
    String name = components[2];
    String address = components[3];
    try {
      _store.registarCliente(id, name, address);
    } catch (ClientKeyDuplicatedException e ){
      throw new DuplicateClientKeyException(e.getMessage());
    }
  }

  // Format: BOX|id|tipo-de-serviço|id-fornecedor|preço|valor-crítico|exemplares
  private void parseBox(String line, String[] components) throws BadEntryException, DuplicateProductKeyException,
                        UnknownServiceTypeException, UnknownSupplierKeyException{
    if (components.length != 7)
      throw new BadEntryException("wrongr number of fields in box description  (7) " + line);


    String id = components[1];
    String tipo = components[2];
    String fornecedor = components[3];
    int preco = Integer.parseInt(components[4]);
    int valorCritico = Integer.parseInt(components[5]);
    int quantidade = Integer.parseInt(components[6]);
    try{
      _store.registarCaixa(id, preco, valorCritico, fornecedor, tipo, quantidade);
    } catch(ProductKeyDuplicatedException e){
      throw new DuplicateProductKeyException(id);
    } catch (ServiceTypeUnknownException e){
      throw new UnknownServiceTypeException(tipo);
    } catch(SupplierUnknownException q){
      throw new UnknownSupplierKeyException(fornecedor);
    }

  }

  // Format: BOOK|id|título|autor|isbn|id-fornecedor|preço|valor-crítico|exemplares
  private void parseBook(String line, String[] components) throws BadEntryException, DuplicateProductKeyException,
          UnknownSupplierKeyException{
   if (components.length != 9)
      throw new BadEntryException("Invalid number of fields (9) in box description: " + line);

    String id = components[1];
    String titulo = components[2];
    String autor = components[3];
    String ISBN = components[4];
    String fornecedor = components[5];
    int preco = Integer.parseInt(components[6]);
    int valorCritico = Integer.parseInt(components[7]);
    int quantidade = Integer.parseInt(components[8]);
    try {
      _store.registarLivro(id, autor, titulo, ISBN, preco, valorCritico, fornecedor, quantidade);
    } catch (ProductKeyDuplicatedException e){
      throw new DuplicateProductKeyException(id);
    } catch(SupplierUnknownException q){
      throw new UnknownSupplierKeyException(fornecedor);
    }
  }

  // Format: CONTAINER|id|tipo-de-serviço|nível-de-serviço|id-fornecedor|preço|valor-crítico|exemplares
  private void parseContainer(String line, String[] components) throws BadEntryException, DuplicateProductKeyException,
                              UnknownServiceTypeException, UnknownServiceLevelException, UnknownSupplierKeyException{
    if (components.length != 8)
      throw new BadEntryException("Invalid number of fields (8) in container description: " + line);

    String id = components[1];
    String tipo = components[2];
    String nivel = components[3];
    String fornecedor = components[4];
    int preco = Integer.parseInt(components[5]);
    int valorCritico = Integer.parseInt(components[6]);
    int quantidade = Integer.parseInt(components[7]);
    try{
      _store.registarContentor(id, preco, valorCritico, fornecedor, tipo, nivel, quantidade);
    } catch(ProductKeyDuplicatedException e){
      throw new DuplicateProductKeyException(id);
    } catch (ServiceTypeUnknownException e){
      throw new UnknownServiceTypeException(tipo);
    } catch (ServiceLevelUnknownException e){
      throw new UnknownServiceLevelException(nivel);
    } catch(SupplierUnknownException q){
    throw new UnknownSupplierKeyException(fornecedor);
  }
  }
}