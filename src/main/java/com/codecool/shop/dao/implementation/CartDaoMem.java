package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.CartDao;
import com.codecool.shop.model.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CartDaoMem implements CartDao {
    private ArrayList<Product> cart = new ArrayList<>();
    private static CartDaoMem instance = null;

    private CartDaoMem(){};

    public static CartDao getInstance() {
        if (instance == null) {
            instance = new CartDaoMem();
        }
        return instance;
    }

    @Override
    public void add(Product product) {
        this.cart.add(product);
    }

    @Override
    public void removeProduct(Product product) {
        for (Product p: cart ){
            if (p.getName().equals(product.getName())){
                cart.remove(p);
                break;
            }
        }
    }

    @Override
    public void removeAllProducts(Product product) {
        while(this.cart.contains(product)){
            this.cart.remove(product);
        }
        System.out.println(this.cart);
    }

    @Override
    public void clearCart() {
        cart.clear();
    }

    @Override
    public ArrayList<Product> getAllProducts() {
        return cart;
    }

    @Override
    public HashMap<Product, Integer> countProducts() {
        HashMap<Product, Integer> productNumber = new HashMap<>();
        for(Product p : cart) {
            if(productNumber.containsKey(p)) {
                int prodNum;
                prodNum = productNumber.get(p);
                prodNum += 1;
                productNumber.put(p, prodNum);
            }
            else {
                productNumber.put(p, 1);
            }
        }
        return productNumber;
    }

    @Override
    public Double GetCartTotal() {
        Double cartTotal = 0.0;
        HashMap<Product, Integer> mapForMoney = this.countProducts();
        for (Map.Entry<Product, Integer> pair: mapForMoney.entrySet()){
            cartTotal += Double.parseDouble(pair.getKey().getPrice().replace("USD", "")) * pair.getValue();
        }
        return cartTotal;
    }
}
