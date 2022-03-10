package com.codecool.shop.config;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.math.BigDecimal;

@WebListener
public class Initializer implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        SupplierDao supplierDataStore = SupplierDaoMem.getInstance();

        //setting up a new supplier
        Supplier amazon = new Supplier("Amazon", "Digital content and services");
        supplierDataStore.add(amazon);
        Supplier lenovo = new Supplier("Lenovo", "Computers");
        supplierDataStore.add(lenovo);
        Supplier apple = new Supplier("Apple", "Digital");
        supplierDataStore.add(apple);

        //setting up a new product category
        ProductCategory tablet = new ProductCategory("Tablet", "Hardware", "A tablet computer, commonly shortened to tablet, is a thin, flat mobile computer with a touchscreen display.");
        productCategoryDataStore.add(tablet);
        ProductCategory phone = new ProductCategory("Phone", "Hardware", "A powerful phone with a brilliant OS.");
        productCategoryDataStore.add(phone);
        ProductCategory watch = new ProductCategory("Watch", "Hardware", "A powerful watch with a brilliant OS.");
        productCategoryDataStore.add(watch);
        ProductCategory laptop = new ProductCategory("Laptop", "Hardware", "A powerful laptop with a brilliant OS.");

        //setting up products and printing it
        productDataStore.add(new Product("Amazon Fire", new BigDecimal("49.9"), "USD", "Fantastic price. Large content ecosystem. Good parental controls. Helpful technical support.", tablet, amazon));
        productDataStore.add(new Product("Lenovo IdeaPad Miix 700", new BigDecimal("479"), "USD", "Keyboard cover is included. Fanless Core m5 processor. Full-size USB ports. Adjustable kickstand.", tablet, lenovo));
        productDataStore.add(new Product("Amazon Fire HD 8", new BigDecimal("89"), "USD", "Amazon's latest Fire HD 8 tablet is a great value for media consumption.", tablet, amazon));
        productDataStore.add(new Product("Iphone 13", new BigDecimal("799"), "USD", "Apple latest phone with an advance dual-camera system, All-screen OLED display 6.1 Inch and the new A15 Bionic chip.", phone, apple));
        productDataStore.add(new Product("Apple Watch Series 7", new BigDecimal("399"), "USD", "The Sport Band is made from a durable yet surprisingly soft high-performance fluoroelastomer with an innovative pin-and-tuck closure.", watch, apple));
        productDataStore.add(new Product("MacBook Air", new BigDecimal("1249"), "USD", "Apple M1 chip with 8‑core CPU, 8‑core GPU, and 16‑core Neural Engine, 512GB SSD storage, Retina display with True Tone.", laptop, apple));
    }
}
