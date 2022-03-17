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

@WebServlet(urlPatterns = {"/add-cart/*"})
public class AddToCartController extends HttpServlet {
    private String productId = "";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        //        resp.sendRedirect(req.getContextPath() + "/");
//        String uri = req.getRequestURI();
//        String product = uri.substring(10).replace("%20", " ");
//
//        CartDao cart = CartDaoMem.getInstance();
//        ProductDao productData = ProductDaoMem.getInstance();
//        System.out.println("Am ajuns 3");
//        cart.add(getProduct(product, productData.getAll()));
        resp.setContentType("application/json");
        processUrlParams(req);
        ProductDao productData = ProductDaoMem.getInstance();
        CartDao cart = CartDaoMem.getInstance();
        Product product = productData.find(productId);
        System.out.println("produsul este: " + product);
        cart.add(product);
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

    private Product getProduct(String product, List<Product> products) {
        for (Product p: products) {
            if (p.getName().equals(product)) return p;
        }
        return products.get(0);
    }
}
