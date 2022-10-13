package edu.miu.cs590.addressservice.exception;

public class AddressNotFound extends RuntimeException{
    public AddressNotFound(String message) {
        super(message);
    }
}
