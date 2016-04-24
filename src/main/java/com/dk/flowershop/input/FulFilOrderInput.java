package com.dk.flowershop.input;

import com.dk.flowershop.CatalogCode;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class FulFilOrderInput {

    private List<Item> items;

    @Data
    @NoArgsConstructor
    public static class Item {

        private CatalogCode catalogCode;

        private int quantity;
    }
}
