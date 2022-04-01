package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.ShoppingCartDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.ShoppingCartDaoMem;
import com.codecool.shop.model.ShoppingCart;
import com.codecool.shop.service.ProductService;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = {"/order"})
public class OrderController  extends HttpServlet{
    ProductDao productDataStore = ProductDaoMem.getInstance();
    ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
    ShoppingCartDao shoppingCartDao = ShoppingCartDaoMem.getInstance();
    ProductService service = new ProductService(productDataStore, productCategoryDataStore, shoppingCartDao);
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
        String userID;
        HttpSession session = req.getSession();
        if(session.getAttribute("userId") != null) {
            userID = (String) session.getAttribute("userId");
        }else{
            userID = req.getSession().getId();
        }
        ShoppingCart cart = service.getShoppingCartByUserId(userID);
        String myCart = new Gson().toJson(cart);
        JsonParser jp = new JsonParser();
        JsonElement element = jp.parse(myCart);


        context.setVariable("mycart",myCart);
        engine.process("order/order.html", context, resp.getWriter());
    }
}
