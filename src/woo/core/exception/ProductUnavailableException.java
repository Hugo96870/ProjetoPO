package woo.core.exception;

import pt.tecnico.po.ui.DialogException;

public class ProductUnavailableException extends Exception{
    private String _key;

    public ProductUnavailableException(String key){
        _key=key;
    }

    public String getMessage(){
        return _key;
    }
}