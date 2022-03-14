package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
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
import java.util.Map;

@WebServlet(urlPatterns = {"/"})
public class ProductController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        ProductService productService = new ProductService(productDataStore,productCategoryDataStore);

        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());

//        if (page != null) {
//            for (ProductCategory pct : productCategoryDataStore.getAll()) {
//                if (page.equals(pct.getName().toLowerCase())){
//                    context.setVariable("category", productService.getProductCategory(pct.getId()));
//                    context.setVariable("products", productService.getProductsForCategory(pct.getId()));
//                } else {
//                    context.setVariable("category", productService.getProductCategory(1));
//                    context.setVariable("products", productService.getProductsForCategory(1));
//                }
//            }
//        } else if (supplier != null) {
//            context.setVariable("products", productService.getSupplierDao(suplier));
//            context.setVariable("currentSupplier", supplier);
//        } else {
//            context.setVariable("categoty", productService.getAllCategories());
//            System.out.println("category received " + productService.getAllCategories());
//            context.setVariable("products", productService.getAllProducts());
//            System.out.println("products received" + productService.getAllProducts());
//        }
//
//        context.setVariable("supplierList", supplierDao.getAll());
//        System.out.println("supplier list is " + supplierDao.getAll());
//
//        context.setVariable("categories", productCategoryDataStore.getAll());
//
//        engine.process("product/index.html", context, resp.getWriter());


        context.setVariable("category", productService.getProductCategory(1));
        context.setVariable("products", productDataStore.getAll());
        // // Alternative setting of the template context
        // Map<String, Object> params = new HashMap<>();
        // params.put("category", productCategoryDataStore.find(1));
        // params.put("products", productDataStore.getBy(productCategoryDataStore.find(1)));
        // context.setVariables(params);
        engine.process("product/index.html", context, resp.getWriter());
    }

}
