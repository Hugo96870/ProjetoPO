package woo.core;

public class Livro extends Produto{
    private String _autor;
    private String _titulo;
    private String _ISBN;

    public Livro(String id, int preco, int valorCritico, String fornecedor, String autor, String ISBN, String titulo, int quantidade){
        super(id, preco, valorCritico, quantidade, fornecedor, TipoDeProduto.BOOK);
        _autor = autor;
        _ISBN=ISBN;
        _titulo=titulo;
    }

    public String toStringProduto(){
        return this.getTipo() + "|" + this.getId() + "|" + this.getFornecedor() + "|" + this.getPreco() +
                "|" + this.getPrecoCritico() + "|" + this.getQuantidade() + "|" + this.getTitulo() + "|" +
                this.getAutor() + "|" + this.getISBN();
    }

    public String getAutor(){
        return _autor;
    }
    public String getISBN(){
        return _ISBN;
    }
    public String getTitulo(){
        return _titulo;
    }
}