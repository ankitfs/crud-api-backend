package com.ankit.exception;

import lombok.Data;

@Data
public class ApplicationException extends RuntimeException{

    private String message;

    public ApplicationException(){}

    public ApplicationException(String message) {
       super();
       this.message = message;
    }
}
