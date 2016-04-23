package com.dk.flowershop;


import com.dk.flowershop.exception.FlowerShopException;
import org.junit.Test;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static junit.framework.Assert.assertEquals;

public class BundleOptimizerTest {

    @Test
    public void shouldOptimizeBundles() throws Exception {
        int[] bundles = {3, 5, 9};
        int target = 13;

        BundleOptimizer bundleOptimizer = new BundleOptimizer(bundles, target);
        bundleOptimizer.optimize();

        List<Integer> expectedCombo = newArrayList(3, 5, 5);
        assertEquals(3, bundleOptimizer.getValidCombos().size());
        assertEquals(expectedCombo, bundleOptimizer.getValidCombos());
    }

    @Test(expected = FlowerShopException.class)
    public void shouldFailIfTargetIsInvalid() throws Exception {
        int[] bundles = {3, 5, 9};
        int target = 0;

        BundleOptimizer bundleOptimizer = new BundleOptimizer(bundles, target);
        bundleOptimizer.optimize();
    }

    @Test(expected = FlowerShopException.class)
    public void shouldFailIfBundlesAreInvalid() throws Exception {
        int[] bundles = null;
        int target = 0;

        BundleOptimizer bundleOptimizer = new BundleOptimizer(bundles, target);
        bundleOptimizer.optimize();
    }


}