package com.dk.flowershop;

import java.util.Map;

import static com.dk.flowershop.CatalogCode.*;
import static com.google.common.collect.Maps.newHashMap;

/**
 * Created by dkulkarni on 24/04/16.
 */
public class CatalogFactory {

    private static Catalog catalog = new Catalog();

    public static Catalog.Product getProductForCode(CatalogCode catalogCode) {
        return catalog.getProducts().get(catalogCode);
    }

    public static void build() {
        Map<CatalogCode, Catalog.Product> products = newHashMap();

        Catalog.Product r12Product = new Catalog.Product();
        Map<Integer, Double> r12bundleOptions = newHashMap();
        r12bundleOptions.put(5, 6.99);
        r12bundleOptions.put(10, 12.99);
        r12Product.setCost(r12bundleOptions);
        products.put(R12, r12Product);

        Catalog.Product l09Product = new Catalog.Product();
        Map<Integer, Double> l09bundleOptions = newHashMap();
        l09bundleOptions.put(3, 9.95);
        l09bundleOptions.put(6, 16.95);
        l09bundleOptions.put(9, 24.95);
        l09Product.setCost(l09bundleOptions);
        products.put(L09, l09Product);

        Catalog.Product t58Product = new Catalog.Product();
        Map<Integer, Double> t58BundleOptions = newHashMap();
        t58BundleOptions.put(3, 5.95);
        t58BundleOptions.put(5, 9.95);
        t58BundleOptions.put(9, 16.99);
        t58Product.setCost(r12bundleOptions);
        products.put(T58, t58Product);

        catalog.setProducts(products);
    }
}
