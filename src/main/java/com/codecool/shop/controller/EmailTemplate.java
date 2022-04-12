package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.ShoppingCartDao;
import com.codecool.shop.dao.implementation.memory.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.memory.ProductDaoMem;
import com.codecool.shop.dao.implementation.memory.ShoppingCartDaoMem;
import com.codecool.shop.model.LineItem;
import com.codecool.shop.model.Order;
import com.codecool.shop.model.ShoppingCart;
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
import java.util.List;

@WebServlet(urlPatterns = {"/template"})
public class EmailTemplate extends HttpServlet {
    ProductDao productDataStore = ProductDaoMem.getInstance();
    ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
    ShoppingCartDao shoppingCartDao = ShoppingCartDaoMem.getInstance();
    ProductService productService = new ProductService(productDataStore,productCategoryDataStore, shoppingCartDao);
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        ShoppingCartDao shoppingCartDao = ShoppingCartDaoMem.getInstance();
        ProductService productService = new ProductService(productDataStore,productCategoryDataStore, shoppingCartDao);

        HttpSession userSession = req.getSession();
        String userId = userSession.getId();

        User user = productService.getUserById(userId);
        String userEmail = user.getEmail();
        List<Order> orders = productService.getOrderByUserId(userId);
        Order order = orders.get(0);
        ShoppingCart shoppingCart = order.getShoppingCart();

        int index = 1;
        for (LineItem line: shoppingCart.getLineItems()) {
            context.setVariable("productitself", index++);
            context.setVariable("prodname", line.getProduct().getName());
            context.setVariable("prodprice", line.getProduct().getPrice());
            context.setVariable("prodquantit", line.getQuantity());

        }
        context.setVariable("totalprice", shoppingCart.getTotalPrice());
        engine.process("emailtemplate/emailtemplate.html", context, resp.getWriter());
    }
}


