package woo.core.exception;

import pt.tecnico.po.ui.DialogException;

public class FileOpenErrorException extends Exception{
    private String _key;

    public FileOpenErrorException(String key){
        _key=key;
    }

    public String getMessage(){
        return _key;
    }
}