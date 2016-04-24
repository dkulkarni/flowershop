package com.dk.flowershop;

import java.util.Map;

public class Catalog {

    private Map<CatalogCode, Product> products;

    public Map<CatalogCode, Product> getProducts() {
        return products;
    }

    public void setProducts(Map<CatalogCode, Product> products) {
        this.products = products;
    }

    public static class Product {

        private String title;

        private Map<Integer, Double> cost;

        public Map<Integer, Double> getCost() {
            return cost;
        }

        public void setCost(Map<Integer, Double> cost) {
            this.cost = cost;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
