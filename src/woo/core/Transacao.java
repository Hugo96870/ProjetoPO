package woo.core;

public class Transacao{
    protected int _id;
    protected static int _idContador = 0;

    public Transacao() {
        _id = _idContador;
        _idContador += 1;
    }

    public int getID(){
        return _id;
    }
}