package woo.core.exception;

import pt.tecnico.po.ui.DialogException;

public class ServiceLevelUnknownException extends Exception{
    private String _key;

    public  ServiceLevelUnknownException(String key){
        _key=key;
    }

    public String getMessage(){
        return _key;
    }
}