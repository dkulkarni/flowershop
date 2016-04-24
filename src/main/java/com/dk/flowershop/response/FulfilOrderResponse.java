package com.dk.flowershop.response;

import com.dk.flowershop.CatalogCode;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

@Data
public class FulfilOrderResponse {


    private Order order;

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
            public static class Bundle {

                private Integer quantity;

                private Integer bundleType;

                private Double unitSellingPrice;
            }

        }
    }
}
