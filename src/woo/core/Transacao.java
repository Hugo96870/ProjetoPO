package woo.core;
import java.io.Serializable;

public class Transacao implements Serializable{
    protected int _id;
    protected static int _idContador = 0;

    public Transacao(int nrID) {
        _idContador = nrID;
        _id = _idContador;
        _idContador += 1;
    }

    public int getID(){
        return _id;
    }
}