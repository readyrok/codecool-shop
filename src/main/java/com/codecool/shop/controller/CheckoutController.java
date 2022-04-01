package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.config.Util;
import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.ShoppingCartDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.ShoppingCartDaoMem;
import com.codecool.shop.model.*;
import com.codecool.shop.service.ProductService;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

// ORDER CHECKOUT
@WebServlet(urlPatterns = {"/checkout"})
public class CheckoutController extends HttpServlet {

    ProductDao productDataStore = ProductDaoMem.getInstance();
    ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
    ShoppingCartDao shoppingCartDao = ShoppingCartDaoMem.getInstance();
    ProductService productService = new ProductService(productDataStore, productCategoryDataStore, shoppingCartDao);

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
        HttpSession session = req.getSession();
        if (session.getAttribute("userId") != null) {
            String userId = (String) session.getAttribute("userId");
            User user = productService.getRegisteredUserById(userId);
            String username = user.getName();
            context.setVariable("user", user);
            context.setVariable("username", username);
        }
        engine.process("checkout/checkout.html", context, resp.getWriter());
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String firstName =  Util.enCodeHU(req.getParameter("firstName"));
        String lastName =  Util.enCodeHU(req.getParameter("lastName"));
        String email = req.getParameter("email");
        String phone = req.getParameter("phone");
        String bilCountry =  Util.enCodeHU(req.getParameter("bilCountry"));
        String bilCity =  Util.enCodeHU(req.getParameter("bilCity"));
        int bilZip = Integer.parseInt(req.getParameter("bilZip"));
        String bilStreet = Util.enCodeHU(req.getParameter("bilStreet"));
        int bilHouse = Integer.parseInt(req.getParameter("bilHouse"));
        String shipCountry =  Util.enCodeHU(req.getParameter("shipCountry"));
        String shipCity =  Util.enCodeHU(req.getParameter("shipCity"));
        int shipZip = Integer.parseInt(req.getParameter("shipZip"));
        String shipStreet = Util.enCodeHU(req.getParameter("shipStreet"));
        int shipHouse = Integer.parseInt(req.getParameter("shipHouse"));
        BillingAddress newBilling = new BillingAddress(bilCountry, bilCity, bilZip, bilStreet, bilHouse);
        ShippingAddress newShipping = new ShippingAddress(shipCountry, shipCity, shipZip, shipStreet, shipHouse);
        HttpSession session = req.getSession();
        String userId;
        if (session.getAttribute("userId") != null){
            userId = (String) session.getAttribute("userId");
            User user = productService.getRegisteredUserById(userId);
            user.setShippingAddress(newShipping);
            user.setBillingAddress(newBilling);
        } else{
            userId = session.getId();
            User newUser = new User(userId, firstName, lastName, email, phone, newBilling, newShipping);
            productService.addUser(newUser);
        }

        try {
            AdminLog.saveToJSON(userId, newBilling, newShipping, "Check address");
        } catch (Exception e) {
            e.printStackTrace();
        }

        resp.sendRedirect(req.getContextPath() + "/payment");

    }
}


