package com.bateng.security.core.validate.code;

import org.springframework.security.core.AuthenticationException;

public class ValidateCodeException extends AuthenticationException {


    private static final long serialVersionUID = 1845720725590226581L;

    public ValidateCodeException(String msg){
        super(msg);
    }

}
