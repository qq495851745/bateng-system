package com.bateng.core.validate.code;

import com.bateng.core.properties.BatengSecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ValidateCodeBeanConfig {

    @Autowired
    private BatengSecurityProperties batengSecurityProperties;

    @Bean
    @ConditionalOnMissingBean(ValidateCodeGenerator.class)
    public ValidateCodeGenerator generate(){
        ImageCodeGenerator imageCodeGenerator = new ImageCodeGenerator();
        imageCodeGenerator.setBatengSecurityProperties(batengSecurityProperties);
        return imageCodeGenerator;
    }
}
