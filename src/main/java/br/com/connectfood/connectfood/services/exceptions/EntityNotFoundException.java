package br.com.connectfood.connectfood.services.exceptions;

public class EntityNotFoundException extends RuntimeException{
    public EntityNotFoundException(String entityName, Object id){
        super(entityName + " com id " + id + " não foi encontrado.");
    }
}
