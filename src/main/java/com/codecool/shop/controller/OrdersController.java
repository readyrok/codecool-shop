package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.ShoppingCartDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.ShoppingCartDaoMem;
import com.codecool.shop.model.User;
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

@WebServlet(urlPatterns = {"/orders"})
public class OrdersController  extends HttpServlet{

    ProductDao productDataStore = ProductDaoMem.getInstance();
    ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
    ShoppingCartDao shoppingCartDao = ShoppingCartDaoMem.getInstance();
    ProductService service = new ProductService(productDataStore, productCategoryDataStore, shoppingCartDao);

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
        HttpSession session = req.getSession();
        String userId;
        String username;
        User user;
        if (session.getAttribute("userId") != null) {
            userId = (String) session.getAttribute("userId");
            user = service.getRegisteredUserById(userId);
            username = user.getName();
        } else {
            userId = req.getSession().getId();
            user = service.getUserById(userId);
            username = null;
        }
        context.setVariable("user", user);
        context.setVariable("username",username);
        engine.process("orders/orders.html", context, resp.getWriter());
    }
}