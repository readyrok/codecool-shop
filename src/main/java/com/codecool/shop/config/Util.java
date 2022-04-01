package com.codecool.shop.config;

import com.codecool.shop.model.ShoppingCart;
import com.codecool.shop.model.User;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.nio.charset.StandardCharsets;

public class Util {
    public static String adminUUID = "03ecc2eaba9a4377ab3f015d61a8d825";
    public static String enCodeHU(String text){
        String hunglishString = text;
        byte[] hunglishBytes = hunglishString.getBytes();
        String asciiString = new String(hunglishBytes, StandardCharsets.ISO_8859_1);
        return asciiString;
    }
    public String generateMailContent(ShoppingCart shoppingCart, User user, TemplateEngine templateEngine) {
        Context context = new Context();
        String filePath = "/emailtemplate/emailtemplate.html";
        //test//
        context.setVariable("LineItemsList", shoppingCart.getLineItems());
        context.setVariable("totalPrice", shoppingCart.getSubTotal());
        context.setVariable("User", user);
        context.setVariable("ShippingAddress", user.getShippingAddress());
        context.setVariable("BillingAddress", user.getBillingAddress());
        return templateEngine.process(filePath, context);

    }
}
