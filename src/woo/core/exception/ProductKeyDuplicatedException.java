package woo.core.exception;

import pt.tecnico.po.ui.DialogException;

public class ProductKeyDuplicatedException extends Exception{
    private String _key;

    public ProductrKeyDuplicatedException(String key){
        _key=key;
    }

    public String getMessage(){
        return _key;
    }
}