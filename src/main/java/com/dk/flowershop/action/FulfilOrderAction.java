package com.dk.flowershop.action;

import com.dk.flowershop.BundleOptimizer;
import com.dk.flowershop.Catalog;
import com.dk.flowershop.CatalogClient;
import com.dk.flowershop.exception.FlowerShopException;
import com.dk.flowershop.input.FulFilOrderInput;
import com.dk.flowershop.response.FulfilOrderResponse;

import java.util.stream.Collectors;

import static java.lang.Math.round;


public class FulfilOrderAction implements Action<FulfilOrderResponse> {

    private CatalogClient catalogClient;

    private FulFilOrderInput input;

    public FulfilOrderAction(CatalogClient catalogClient) {
        this.catalogClient = catalogClient;
    }

    public FulfilOrderAction withInput(FulFilOrderInput input) {
        this.input = input;
        return this;

    }

    public FulfilOrderResponse invoke() throws FlowerShopException {
        FulfilOrderResponse response = new FulfilOrderResponse();
        FulfilOrderResponse.Order order = new FulfilOrderResponse.Order();
        Double sellingPrice = 0.0;

        for (FulFilOrderInput.Item item : input.getItems()) {
            Catalog.Product product = catalogClient.getProduct(item.getCatalogCode());
            FulfilOrderResponse.Order.OrderItem orderItem = buildOrderItem(item, product);
            order.getOrderItems().add(orderItem);
            sellingPrice += orderItem.getSellingPrice();
        }
        order.setSellingPrice(round(sellingPrice * 100.0) / 100.0);
        response.setOrder(order);
        return response;
    }

    private FulfilOrderResponse.Order.OrderItem buildOrderItem(FulFilOrderInput.Item item, Catalog.Product product) throws FlowerShopException {
        int[] bundles = product
                .getCost()
                .keySet()
                .stream()
                .mapToInt(Number::intValue)
                .toArray();

        BundleOptimizer bundleOptimizer = new BundleOptimizer(bundles, item.getQuantity());
        bundleOptimizer.optimize();

        FulfilOrderResponse.Order.OrderItem orderItem = new FulfilOrderResponse.Order.OrderItem();
        orderItem.setCatalogCode(item.getCatalogCode());

        bundleOptimizer
                .getValidCombos()
                .stream()
                .collect(Collectors.groupingBy(x -> x))
                .forEach((k, v) -> {
                    FulfilOrderResponse.Order.OrderItem.Bundle bundle = new FulfilOrderResponse.Order.OrderItem.Bundle();
                    Double itemSellingPrice = orderItem.getSellingPrice();
                    bundle.setBundleType(k);
                    bundle.setQuantity(v.size());
                    bundle.setUnitSellingPrice(product.getCost().get(k));
                    orderItem.addBundle(bundle);
                    itemSellingPrice += (v.size() * product.getCost().get(k));
                    orderItem.setSellingPrice(round(itemSellingPrice * 100.0) / 100.0);
                });

        return orderItem;
    }
}
