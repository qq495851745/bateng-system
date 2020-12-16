package com.bateng.web.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import static com.bateng.utils.ToJsonString.*;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ExceptionControllerHandler {

    @ExceptionHandler(UserNoException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public String handlerUserNoException(UserNoException ex){
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("id",ex.getId());

        return  toDisableCircularReferenceDetectString(map);
    }
}
