package com.dk.flowershop.action;

import com.dk.flowershop.exception.FlowerShopException;

/**
 * Created by dkulkarni on 24/04/16.
 */
public interface Action<T> {

    T invoke() throws FlowerShopException;
}
