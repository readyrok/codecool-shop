package com.codecool.shop.controller;

import com.codecool.shop.dao.CartDao;
import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;

import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.dao.implementation.CartDaoMem;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.service.ProductService;
import com.codecool.shop.config.TemplateEngineUtil;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(urlPatterns = {"/", "index.html"})
public class ProductController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String page = req.getParameter("categoty");
        String supplier = req.getParameter("supplier");
        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        SupplierDao supplierDao = SupplierDaoMem.getInstance();
        ProductService productService = new ProductService(productDataStore,productCategoryDataStore);
        CartDao cart = CartDaoMem.getInstance();

        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());

        if (page != null) {
            for (ProductCategory pd : productCategoryDataStore.getAll()) {
                if (page.equals(pd.getName().toLowerCase())) {
                    context.setVariable("category", productService.getProductCategory(pd.getId()));
                    context.setVariable("products", productService.getProductsForCategory(pd.getId()));
                } else {
                    context.setVariable("category", productService.getProductCategory("1"));
                    context.setVariable("products", productService.getProductsForCategory("1"));
                }
            }
        } else if(supplier != null){
            context.setVariable("products", productService.getSupplierDao(supplier));
            context.setVariable("currentSupplier", supplier);
        }else {
            context.setVariable("category", productDataStore.getAll());
            context.setVariable("products", productService.getEveryProducts());
        }
        context.setVariable("supplierList", supplierDao.getAll());
        context.setVariable("categoryList",productCategoryDataStore.getAll());

        List<ProductCategory> productCategory = productCategoryDataStore.getAll();
        context.setVariable("categories", productService.getProductCategory(productCategory.get(0).getId()));


        context.setVariable("cart", cart);
        

        engine.process("product/index.html", context, resp.getWriter());
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
