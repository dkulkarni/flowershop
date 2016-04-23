package com.dk.flowershop;


import com.dk.flowershop.exception.FlowerShopException;

import java.util.ArrayList;
import java.util.List;

import static com.dk.flowershop.exception.ErrorCode.INVALID_INPUT;

public class BundleOptimizer {

    private int[] optimizedBundle;

    private int target;

    private int[] bundles;

    public BundleOptimizer(int[] bundles, int target) {
        this.target = target;
        this.bundles = bundles;
        this.optimizedBundle = new int[target + 1];
    }

    public void optimize() throws FlowerShopException {
        if (target == 0 || bundles == null || bundles.length < 1) {
            throw new FlowerShopException("Invalid input received for optimizing", INVALID_INPUT);
        }
        int[] bundlesUsed = new int[target + 1];

        bundlesUsed[0] = 0;
        optimizedBundle[0] = 1;

        for (int item = 1; item <= target; item++) {
            int minBundle = item;
            int newBundle = 1;

            for (int bundle : bundles) {
                if (bundle > item)
                    continue;
                if (bundlesUsed[item - bundle] + 1 < minBundle) {
                    minBundle = bundlesUsed[item - bundle] + 1;
                    newBundle = bundle;
                }
            }

            bundlesUsed[item] = minBundle;
            optimizedBundle[item] = newBundle;
        }
    }

    public List<Integer> getValidCombos() {
        List<Integer> combos = new ArrayList<Integer>();
        for (int i = target; i > 0; ) {
            combos.add(this.optimizedBundle[i]);
            i -= this.optimizedBundle[i];
        }
        return combos;
    }


}