package br.com.praticas.mygroup.util;

public class DaoException extends Exception{
    
    public DaoException(String msg){
        super(msg);
    }
    
    public DaoException(Exception e){
        super(e);
    }
    
    public DaoException(String msg, Exception e){
        super(msg, e);
    }
}
