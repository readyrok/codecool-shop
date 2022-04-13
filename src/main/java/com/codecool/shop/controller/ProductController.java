package com.codecool.shop.controller;

import com.codecool.shop.config.Initializer;
import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.config.Util;
import com.codecool.shop.dao.*;
import com.codecool.shop.dao.implementation.database.DatabaseManager;
import com.codecool.shop.dao.implementation.memory.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.memory.ProductDaoMem;
import com.codecool.shop.dao.implementation.memory.ShoppingCartDaoMem;
import com.codecool.shop.dao.implementation.memory.SupplierDaoMem;
import com.codecool.shop.model.ProductCategory;
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

@WebServlet(urlPatterns = {"/"})
public class ProductController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String page = req.getParameter("category");
        String supplier = req.getParameter("supplier");
        DatabaseManager manager = Initializer.getDatabaseManager();
        ProductService productService;
        ProductDao productDataStore;
        ProductCategoryDao productCategoryDataStore;
        SupplierDao supplierDao;
        ShoppingCartDao shoppingCartDao;

        if(manager == null) {
            productDataStore = ProductDaoMem.getInstance();
            productCategoryDataStore = ProductCategoryDaoMem.getInstance();
            supplierDao = SupplierDaoMem.getInstance();
            shoppingCartDao = ShoppingCartDaoMem.getInstance();
        } else {
            productDataStore = manager.getProductDao();
            productCategoryDataStore = manager.getProductCategoryDao();
            supplierDao = manager.getSupplierDao();
            shoppingCartDao = manager.getCartDao();
        }

        productService = new ProductService(productDataStore, productCategoryDataStore, shoppingCartDao);

        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());

        HttpSession session = req.getSession();

        if(session.getAttribute("userId") != null){
            String userId = (String) session.getAttribute("userId");
            System.out.println("index " + userId);
            User currentUser = productService.getRegisteredUserById(userId);
            if(currentUser.getId().equals(Util.adminUUID)){
                context.setVariable("isAdmin" , true);
            }
            context.setVariable("username",currentUser.getName());
        }

        if (page != null) {
            for (ProductCategory pd : productCategoryDataStore.getAll()) {
                if (page.equals(pd.getName().toLowerCase())) {
                    context.setVariable("category", productService.getProductCategory(pd.getId()));
                    context.setVariable("products", productService.getProductsForCategory(pd.getId()));
                } else {
                    context.setVariable("category", productService.getProductCategory(1));
                    context.setVariable("products", productService.getProductsForCategory(1));
                }
            }
        } else if(supplier != null){
            context.setVariable("products", productService.getSupplier(supplier));
            context.setVariable("currentSupplier", supplier);
        }else {
            context.setVariable("category", productDataStore.getAll());
            context.setVariable("products", productService.getEveryProducts());
        }
        context.setVariable("supplierList", supplierDao.getAll());
        context.setVariable("categories", productCategoryDataStore.getAll());

        context.setVariable("categories", productCategoryDataStore.getAll());
        List<ProductCategory> productCategory = productCategoryDataStore.getAll();

        engine.process("product/index.html", context, resp.getWriter());
    }

}
