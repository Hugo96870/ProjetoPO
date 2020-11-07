package woo.core.exception;

import pt.tecnico.po.ui.DialogException;

public class ClientKeyDuplicatedException extends Exception{
    private String _key;

    public ClientKeyDuplicatedException(String key){
        _key=key;
    }

    public String getMessage(){
        return _key;
    }
}