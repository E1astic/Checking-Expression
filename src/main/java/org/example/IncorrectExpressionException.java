package org.example;

public class IncorrectExpressionException extends Exception{
    public IncorrectExpressionException(){
        super();
    }
    public IncorrectExpressionException(String message){
        super(message);
    }
}
