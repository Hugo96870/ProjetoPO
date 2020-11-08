package woo.core.exception;

import pt.tecnico.po.ui.DialogException;

public class ServiceTypeUnknownException extends Exception{
    private String _key;

    public  ServiceTypeUnknownException(String key){
        _key=key;
    }

    public String getMessage(){
        return _key;
    }
}