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
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

@WebServlet(urlPatterns = {"/add-product/*"})
public class AddToCartController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        resp.sendRedirect(req.getContextPath() + "/");
        String uri = req.getRequestURI();
        String product = uri.substring(10).replace("%20", " ");

        CartDao cart = CartDaoMem.getInstance();
        ProductDao productData = ProductDaoMem.getInstance();

        cart.add(getProduct(product, productData.getAll()));
    }

    private Product getProduct(String product, List<Product> products) {
        for (Product p: products) {
            if (p.getName().equals(product)) return p;
        }
        return products.get(0);
    }
}
