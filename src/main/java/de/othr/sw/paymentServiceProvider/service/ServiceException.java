package de.othr.sw.paymentServiceProvider.service;

public class ServiceException extends RuntimeException{
    // ToDo: extends Exception instead of RuntimeException?
    public ServiceException(String msg){
        super(msg);
    }
}
