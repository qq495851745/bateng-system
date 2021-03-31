package com.bateng.security.core.validate.code;


import com.bateng.security.core.properties.BatengSecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;


public class ImageCodeGenerator implements ValidateCodeGenerator {
    @Autowired
    private BatengSecurityProperties batengSecurityProperties;

    public ImageCode generate(ServletWebRequest request){
        int width = ServletRequestUtils.getIntParameter(request.getRequest(),"width",batengSecurityProperties.getValidateCodeProperties().getImageCodeProperties().getWidth());
        int height = ServletRequestUtils.getIntParameter(request.getRequest(),"hight",batengSecurityProperties.getValidateCodeProperties().getImageCodeProperties().getHeight());
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        Graphics g = image.getGraphics();

        Random random = new Random();

        g.setColor(getRandColor(200, 250));
        g.fillRect(0, 0, width, height);
        g.setFont(new Font("Times New Roman", Font.ITALIC, 20));
        g.setColor(getRandColor(160, 200));
        for (int i = 0; i < 155; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int xl = random.nextInt(12);
            int yl = random.nextInt(12);
            g.drawLine(x, y, x + xl, y + yl);
        }

        String sRand = "";
        for (int i = 0; i < batengSecurityProperties.getValidateCodeProperties().getImageCodeProperties().getLength(); i++) {
            String rand = String.valueOf(random.nextInt(10));
            sRand += rand;
            g.setColor(new Color(20 + random.nextInt(110), 20 + random.nextInt(110), 20 + random.nextInt(110)));
            g.drawString(rand, 13 * i + 6, 16);
        }

        g.dispose();

        return new ImageCode(image, sRand, batengSecurityProperties.getValidateCodeProperties().getImageCodeProperties().getExpireIn());
    }

    public BatengSecurityProperties getBatengSecurityProperties() {
        return batengSecurityProperties;
    }

    public void setBatengSecurityProperties(BatengSecurityProperties batengSecurityProperties) {
        this.batengSecurityProperties = batengSecurityProperties;
    }
}
