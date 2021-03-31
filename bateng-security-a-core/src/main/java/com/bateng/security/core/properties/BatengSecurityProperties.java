package com.bateng.security.core.properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;


@Component
public class BatengSecurityProperties {
    @Autowired
    private BrowserProperties browserProperties;

    @Autowired
    private ValidateCodeProperties validateCodeProperties;

    public BrowserProperties getBrowserProperties() {
        return browserProperties;
    }

    public ValidateCodeProperties getValidateCodeProperties() {
        return validateCodeProperties;
    }

    public void setValidateCodeProperties(ValidateCodeProperties validateCodeProperties) {
        this.validateCodeProperties = validateCodeProperties;
    }

    public void setBrowserProperties(BrowserProperties browserProperties) {
        this.browserProperties = browserProperties;
    }
}
