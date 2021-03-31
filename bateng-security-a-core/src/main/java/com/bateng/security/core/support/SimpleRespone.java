package com.bateng.security.core.support;

public class SimpleRespone {
    private Object content;

    public SimpleRespone(Object content) {
        this.content = content;
    }

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }
}
