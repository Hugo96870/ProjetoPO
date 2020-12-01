package woo.core.exception;

import pt.tecnico.po.ui.DialogException;

public class TransactionKeyUnknownException extends Exception{
    private String _key;

    public  TransactionKeyUnknownException(String key){
        _key=key;
    }

    public String getMessage(){
        return _key;
    }
}