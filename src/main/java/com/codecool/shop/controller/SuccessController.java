package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.config.Util;
import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.ShoppingCartDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.ShoppingCartDaoMem;
import com.codecool.shop.model.Order;
import com.codecool.shop.model.ShoppingCart;
import com.codecool.shop.model.User;
import com.codecool.shop.service.ProductService;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.mail.*;
import javax.mail.internet.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

@WebServlet(urlPatterns = {"/success"})
public class SuccessController  extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String from = "cc.fourhorseman";
        String pass = "Qawsed01";

        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(request.getServletContext());
        WebContext context = new WebContext(request, response, request.getServletContext());

        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        ShoppingCartDao shoppingCartDao = ShoppingCartDaoMem.getInstance();
        ProductService productService = new ProductService(productDataStore,productCategoryDataStore, shoppingCartDao);

        HttpSession session = request.getSession();

        Order order;
        String userId;
        User user;
        if (session.getAttribute("userId") != null){
            userId = (String) session.getAttribute("userId");
            user = productService.getRegisteredUserById(userId);
            order = user.getOrders().get(user.getOrders().size()-1);
        } else {
            userId = session.getId();
            List<Order> orders = productService.getOrderByUserId(userId);
            order = orders.get(0);
            user = productService.getUserById(userId);
        }
        context.setVariable("order", order);
        context.setVariable("user", user);



        String userEmail = user.getEmail();
        String[] to = { userEmail
        };
        String subject = "Success Order!";
        ShoppingCart shoppingCart = order.getShoppingCart();


        Properties props = System.getProperties();
        String host = "smtp.gmail.com";
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.user", from);
        props.put("mail.smtp.password", pass);
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");

        Session session2 = Session.getInstance(props);
        MimeMessage msg = new MimeMessage(session2);

        try {
            msg.setFrom(new InternetAddress(from));
            InternetAddress[] toAddress = new InternetAddress[to.length];

            for( int i = 0; i < to.length; i++ ) {
                toAddress[i] = new InternetAddress(to[i]);
            }

            for( int i = 0; i < toAddress.length; i++) {
                msg.addRecipient(Message.RecipientType.TO, toAddress[i]);
            }

            Util util = new Util();
            String email = util.generateMailContent(shoppingCart, user, engine);

            msg.setContent(email, "text/html");
            msg.setSubject(subject); // HERE WE CAN SEE THE MSG
            Transport transport = session2.getTransport("smtp");
            transport.connect(host, from, pass);
            transport.sendMessage(msg, msg.getAllRecipients());
            transport.close();
        }
        catch (AddressException ae) {
            ae.printStackTrace();
        }
        catch (MessagingException me) {
            me.printStackTrace();
        }

        engine.process("payment/success.html", context, response.getWriter());


    }
}