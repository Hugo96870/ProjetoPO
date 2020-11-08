package woo.core.exception;

import pt.tecnico.po.ui.DialogException;

public class SupplierKeyDuplicatedException extends Exception{
    private String _key;

    public SupplierKeyDuplicatedException(String key){
        _key=key;
    }

    public String getMessage(){
        return _key;
    }
}