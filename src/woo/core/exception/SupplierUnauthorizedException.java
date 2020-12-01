package woo.core.exception;

import pt.tecnico.po.ui.DialogException;

public class SupplierUnauthorizedException extends Exception{
    private String _key;

    public  SupplierUnauthorizedException(String key){
        _key=key;
    }

    public String getMessage(){
        return _key;
    }
}