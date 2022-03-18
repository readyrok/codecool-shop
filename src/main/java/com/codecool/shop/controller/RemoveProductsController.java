package com.codecool.shop.controller;

import com.codecool.shop.dao.CartDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.implementation.CartDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.model.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/remove/all/*")
public class RemoveProductsController extends HttpServlet {
    private String productId = "";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        processUrlParams(req);
        ProductDao productData = ProductDaoMem.getInstance();
        CartDao cart = CartDaoMem.getInstance();
        Product product = productData.find(productId);
        System.out.println("produsul de sters este: " + product);
        cart.removeAllProducts(product);
    }

    protected void processUrlParams(HttpServletRequest req) {
        String productParam = req.getParameter("product");
        if(productParam!=null){
            try{
                productId = productParam;
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
    }
}