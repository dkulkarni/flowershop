package com.dk.flowershop.response;

import com.dk.flowershop.CatalogCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

@Data
public class FulfilOrderResponse {


    private Order order;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Order Details:");
        sb.append("\n Order Price: " + order.sellingPrice);
        for (Order.OrderItem item : order.getOrderItems()) {
            sb.append("\n " + item.catalogCode + " " + item.sellingPrice);
            for (Order.OrderItem.Bundle bundle : item.getBundles()) {
                sb.append("\n  " + bundle.quantity + " * " + bundle.bundleType + " @ " + bundle.quantity * bundle.unitSellingPrice);
            }
        }
        return sb.toString();
    }

    @Data
    @EqualsAndHashCode
    public static class Order {

        private Double sellingPrice;

        private List<OrderItem> orderItems = newArrayList();

        @Data
        @EqualsAndHashCode
        public static class OrderItem {

            List<Bundle> bundles = newArrayList();

            private CatalogCode catalogCode;

            private Double sellingPrice = 0.0;

            public void addBundle(Bundle bundle) {
                bundles.add(bundle);
            }

            @Data
            @EqualsAndHashCode
            @AllArgsConstructor
            @NoArgsConstructor
            public static class Bundle {

                private Integer quantity;

                private Integer bundleType;

                private Double unitSellingPrice;
            }

        }
    }
}
