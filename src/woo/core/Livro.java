package woo.core;

public class Livro{
    private String _autor;
    private int _preco;
    private int _valorCritico;
    private String _idLivro;
    private String _idFornecedorLivro;

    public Livro(String id, int preco, int valorCritico, String fornecedor, String autor){
        _autor = autor;
        _preco = preco;
        _valorCritico = valorCritico;
        _idFornecedorLivro = fornecedor;
        _idLivro = id;
    }

    public int getPreco(){
        return _preco;
    }

    public int getValorCritico(){
        return _valorCritico;
    }

    public String getIdLivro(){
        return _idLivro;
    }

    public String getIdFornecedor(){
        return _idFornecedorLivro;
    }

    public String getAutor(){
        return _autor;
    }
}