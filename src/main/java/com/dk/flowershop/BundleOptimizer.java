package com.dk.flowershop;


import com.dk.flowershop.exception.FlowerShopException;
import com.google.common.primitives.Ints;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.ArrayUtils;

import java.util.ArrayList;
import java.util.List;

import static com.dk.flowershop.exception.ErrorCode.INVALID_INPUT;

@Setter
@Getter
public class BundleOptimizer {

    private int[] optimizedBundle;

    private int quantity;

    private int[] bundles;

    public BundleOptimizer(int[] bundles, int quantity) {
        this.quantity = quantity;
        this.bundles = bundles;
        this.optimizedBundle = new int[quantity + 1];
    }

    public BundleOptimizer() {
        this.optimizedBundle = new int[quantity + 1];
    }

    public void optimize() throws FlowerShopException {
        if (quantity == 0 || bundles == null || bundles.length < 1) {
            throw new FlowerShopException("Invalid input received for optimizing", INVALID_INPUT);
        }
        int[] bundlesUsed = new int[quantity + 1];

        bundlesUsed[0] = 0;
        optimizedBundle[0] = 1;

        for (int item = 1; item <= quantity; item++) {
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

    public List<Integer> getValidCombos() throws FlowerShopException {
        List<Integer> combos = new ArrayList<Integer>();
        for (int i = quantity; i > 0; ) {
            if (!Ints.asList(bundles).contains(optimizedBundle[i])) {
                throw new FlowerShopException("Bundles " + ArrayUtils.toString(bundles) + " cannot sum up to " + quantity, INVALID_INPUT);
            }
            combos.add(this.optimizedBundle[i]);
            i -= this.optimizedBundle[i];
        }
        return combos;
    }


}