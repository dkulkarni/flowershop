package com.dk.flowershop;

import com.dk.flowershop.exception.FlowerShopException;

import static com.dk.flowershop.exception.ErrorCode.INVALID_INPUT;

public class CatalogClient {

    public CatalogClient() {
        CatalogFactory.build();
    }

    public Catalog.Product getProduct(CatalogCode catalogCode) throws FlowerShopException {
        if (catalogCode == null) {
            throw new FlowerShopException("CatalogCode cannot be null", INVALID_INPUT);
        }
        return CatalogFactory.getProductForCode(catalogCode);
    }
}
