package com.ac2.ac2.exceptions;



public class RegraNegocioException extends RuntimeException {
    
    public RegraNegocioException(String mensagemErro) {
        super(mensagemErro);
    }
}