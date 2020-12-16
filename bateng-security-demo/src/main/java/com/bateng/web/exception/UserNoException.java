package com.bateng.web.exception;

public class UserNoException extends  RuntimeException {

    private static final long serialVersionUID = -9084873210840898972L;

    private Integer id;
    private String message;

    public UserNoException(Integer id,String message){
        super(message);
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
