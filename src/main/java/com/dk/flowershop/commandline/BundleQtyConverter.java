package com.dk.flowershop.commandline;

import com.beust.jcommander.IStringConverter;
import com.beust.jcommander.IStringConverterFactory;
import com.dk.flowershop.CatalogCode;

public class BundleQtyConverter implements IStringConverter<BundleQty> {
    @Override
    public BundleQty convert(String value) {
        BundleQty result = new BundleQty();
        String[] s = value.split(":");
        result.bundle = CatalogCode.valueOf(s[0]);
        result.quantity = Integer.parseInt(s[1]);
        return result;
    }

    public static class Factory implements IStringConverterFactory {
        public Class<? extends IStringConverter<?>> getConverter(Class forType) {
            if (forType.equals(BundleQty.class)) return BundleQtyConverter.class;
            else return null;
        }

    }
}
