package com.dk.flowershop.action;

import com.dk.flowershop.CatalogClient;
import com.dk.flowershop.CatalogCode;
import com.dk.flowershop.input.FulFilOrderInput;
import com.dk.flowershop.response.FulfilOrderResponse;
import org.junit.Before;
import org.junit.Test;

import static com.dk.flowershop.CatalogCode.*;
import static com.google.common.collect.Lists.newArrayList;
import static junit.framework.Assert.*;

public class FulfilOrderActionTest {

    CatalogClient catalogClient;
    FulfilOrderAction action;

    @Before
    public void setup() {
        catalogClient = new CatalogClient();
        action = new FulfilOrderAction(catalogClient);
    }

    @Test
    public void shouldCreateValidResponse() throws Exception {
        FulFilOrderInput input = new FulFilOrderInput();
        FulFilOrderInput.Item item = new FulFilOrderInput.Item();
        item.setCatalogCode(CatalogCode.L09);
        item.setQuantity(15);
        input.setItems(newArrayList(item));

        FulfilOrderResponse fulfilOrderResponse = action
                .withInput(input)
                .invoke();

        FulfilOrderResponse.Order.OrderItem orderItem = fulfilOrderResponse.getOrder().getOrderItems().get(0);

        FulfilOrderResponse.Order.OrderItem.Bundle expectedBundle = new FulfilOrderResponse.Order.OrderItem.Bundle();
        expectedBundle.setUnitSellingPrice(24.95);
        expectedBundle.setBundleType(9);
        expectedBundle.setQuantity(1);

        FulfilOrderResponse.Order.OrderItem.Bundle expectedBundle2 = new FulfilOrderResponse.Order.OrderItem.Bundle();
        expectedBundle.setUnitSellingPrice(16.95);
        expectedBundle.setBundleType(6);
        expectedBundle.setQuantity(1);

        assertNotNull(fulfilOrderResponse);
        assertEquals(41.90, fulfilOrderResponse.getOrder().getSellingPrice());
        assertTrue(orderItem.getBundles().contains(expectedBundle));
        assertTrue(orderItem.getBundles().contains(expectedBundle2));
    }

    @Test
    public void shouldCreateResponseWithManyItems() throws Exception {
        FulFilOrderInput input = inputWithThreeItems();
        FulfilOrderResponse fulfilOrderResponse = action
                .withInput(input)
                .invoke();

        assertEquals(outputWithThreeItems(), fulfilOrderResponse);
    }

    private FulFilOrderInput inputWithThreeItems() {
        FulFilOrderInput input = new FulFilOrderInput();
        FulFilOrderInput.Item item1 = new FulFilOrderInput.Item();
        item1.setCatalogCode(CatalogCode.L09);
        item1.setQuantity(15);

        FulFilOrderInput.Item item2 = new FulFilOrderInput.Item();
        item2.setCatalogCode(R12);
        item2.setQuantity(10);

        FulFilOrderInput.Item item3 = new FulFilOrderInput.Item();
        item3.setCatalogCode(CatalogCode.T58);
        item3.setQuantity(13);
        input.setItems(newArrayList(item2, item1, item3));

        return input;
    }

    private FulfilOrderResponse outputWithThreeItems() {
        FulfilOrderResponse response = new FulfilOrderResponse();
        FulfilOrderResponse.Order order = new FulfilOrderResponse.Order();
        order.setSellingPrice(80.74);

        FulfilOrderResponse.Order.OrderItem orderItem1 = new FulfilOrderResponse.Order.OrderItem();
        orderItem1.setSellingPrice(12.99);
        orderItem1.setCatalogCode(R12);
        orderItem1.addBundle(new FulfilOrderResponse.Order.OrderItem.Bundle(1, 10, 12.99));

        FulfilOrderResponse.Order.OrderItem orderItem2 = new FulfilOrderResponse.Order.OrderItem();
        orderItem2.setSellingPrice(41.90);
        orderItem2.setCatalogCode(L09);
        orderItem2.addBundle(new FulfilOrderResponse.Order.OrderItem.Bundle(1, 6, 16.95));
        orderItem2.addBundle(new FulfilOrderResponse.Order.OrderItem.Bundle(1, 9, 24.95));

        FulfilOrderResponse.Order.OrderItem orderItem3 = new FulfilOrderResponse.Order.OrderItem();
        orderItem3.setSellingPrice(25.85);
        orderItem3.setCatalogCode(T58);
        orderItem3.addBundle(new FulfilOrderResponse.Order.OrderItem.Bundle(1, 3, 5.95));
        orderItem3.addBundle(new FulfilOrderResponse.Order.OrderItem.Bundle(2, 5, 9.95));

        order.setOrderItems(newArrayList(orderItem1, orderItem2, orderItem3));
        response.setOrder(order);

        return response;
    }

}