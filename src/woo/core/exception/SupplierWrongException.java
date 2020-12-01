package woo.core.exception;

import pt.tecnico.po.ui.DialogException;

public class SupplierWrongException extends Exception{
    private String _keyS;
    private String _keyP;

    public  SupplierWrongException(String keyS, String keyP){
        _keyS = keyS;
        _keyP = keyP;
    }

    public String getMessage(){
        return _keyP;
    }
}