package woo.core.exception;

import pt.tecnico.po.ui.DialogException;

public class SupplierUnknownException extends Exception{
    private String _key;

    public SupplierUnknownException(String key){
        _key=key;
    }

    public String getMessage(){
        return _key;
    }
}