package br.com.connectfood.connectfood.domain.services.exceptions;

public class ConflictException extends RuntimeException{
    public ConflictException(String message){
        super(message);
    }
}
