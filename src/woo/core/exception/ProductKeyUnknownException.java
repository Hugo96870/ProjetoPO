package woo.core.exception;

import pt.tecnico.po.ui.DialogException;

public class ProductKeyUnknownException extends Exception{
    private String _key;

    public ProductKeyUnknownException(String key){
        _key=key;
    }

    public String getMessage(){
        return _key;
    }
}