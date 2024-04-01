package com.example.academiacx.handlers.exceptions;

public class ParametroInvalidoException extends RuntimeException {

    public ParametroInvalidoException(String mns) {
        super(mns);
    }

    public ParametroInvalidoException(String mns, Throwable causa) {
        super(mns, causa);
    }
}
