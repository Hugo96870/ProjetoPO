package woo.core.exception;

import pt.tecnico.po.ui.DialogException;

public class InvalidDateToAdvanceException extends Exception{
    
    private int _key;

    public InvalidDateToAdvanceException(int key){
        _key=key;
    }


    public int getValue(){
        return _key;
    }
}