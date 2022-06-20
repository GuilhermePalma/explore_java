package com.guilhermepalma.streamexercices.exceptions;

public class NotFoundException extends Exception{
    public NotFoundException() {
        super("not found the requested resource");
    }
}
