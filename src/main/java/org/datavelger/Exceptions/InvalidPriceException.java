package org.datavelger.Exceptions;

public class InvalidPriceException extends Exception{
    public InvalidPriceException() {super("Price must be a positive value.");}
}
