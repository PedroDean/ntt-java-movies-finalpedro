package com.example.academiacx.handlers.exceptions;

public class RecursoNaoEntradoException extends RuntimeException{

    public RecursoNaoEntradoException(String mns) {
        super(mns);
    }

    public RecursoNaoEntradoException(String mns, Throwable causa) {
        super(mns, causa);
    }
}
