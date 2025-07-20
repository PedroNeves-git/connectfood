package br.com.connectfood.connectfood.services.exceptions;

public class UnauthorizedException extends RuntimeException{
    public UnauthorizedException(String message){
        super(message);
    }
}
