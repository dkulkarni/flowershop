package com.dk.flowershop;

import com.dk.flowershop.exception.FlowerShopException;
import org.junit.Before;
import org.junit.Test;

import static com.dk.flowershop.CatalogCode.R12;
import static junit.framework.Assert.assertNotNull;

public class CatalogClientTest {

    CatalogClient client;

    @Before
    public void setup() {
        client = new CatalogClient();
    }

    @Test
    public void shouldFetchProductFromFactory() throws Exception {
        Catalog.Product product = client.getProduct(R12);
        assertNotNull(product);
    }

    @Test(expected = FlowerShopException.class)
    public void shouldFailForInvalidInput() throws Exception {
        client.getProduct(null);
    }

}