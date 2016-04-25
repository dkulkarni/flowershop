package com.dk.flowershop.input;

import com.dk.flowershop.CatalogCode;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

@Data
@NoArgsConstructor
public class FulFilOrderInput {

    private List<Item> items = newArrayList();

    public void addItem(Item item) {
        items.add(item);
    }
    @Data
    @NoArgsConstructor
    public static class Item {

        private CatalogCode catalogCode;

        private int quantity;
    }
}
