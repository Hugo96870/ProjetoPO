package woo.core.exception;

import pt.tecnico.po.ui.DialogException;

public class InvalidClientKeyException extends Exception{
    private String _key;

    public InvalidClientKeyException(String key){
        _key=key;
    }

    public String getMessage(){
        return _key;
    }
}