package com.dk.flowershop.action;

import com.dk.flowershop.CatalogClient;
import com.dk.flowershop.CatalogCode;
import com.dk.flowershop.input.FulFilOrderInput;
import com.dk.flowershop.response.FulfilOrderResponse;
import org.junit.Before;
import org.junit.Test;

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

}