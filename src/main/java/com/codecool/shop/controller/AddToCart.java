package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.ShoppingCartDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.ShoppingCartDaoMem;
import com.codecool.shop.model.AdminLog;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.User;
import com.codecool.shop.service.ProductService;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet(urlPatterns = {"/addtocart"})
public class AddToCart extends HttpServlet {

    private Gson gson = new Gson();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        ShoppingCartDao shoppingCartDao = ShoppingCartDaoMem.getInstance();
        ProductService productService = new ProductService(productDataStore, productCategoryDataStore, shoppingCartDao);

        String jsonString = "";
        try {
            String line = "";
            BufferedReader reader = req.getReader();
            while ((line = reader.readLine()) != null)
                jsonString += line;
        } catch (Exception e) {
            e.printStackTrace();
        }
        int productId = Integer.parseInt(jsonString);
        Product product = productService.getProductById(productId);

        HttpSession session = req.getSession();
        if (session.getAttribute("userId") != null) {
            String userId = (String) session.getAttribute("userId");
            User user = productService.getRegisteredUserById(userId);
            user.addProduct(product);
        } else {
            String userID = req.getSession().getId();
            try {
                AdminLog.saveToJSON(userID, product, "Product add to cart");
            } catch (Exception e) {
                e.printStackTrace();
            }
            productService.addProductToCart(userID, productId);
        }
    }

}
