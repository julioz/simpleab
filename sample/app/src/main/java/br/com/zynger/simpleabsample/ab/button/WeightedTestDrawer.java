package br.com.zynger.simpleabsample.ab.button;

import java.util.Collection;

import br.com.zynger.simpleab.ABTestVariant;
import br.com.zynger.simpleab.AbstractABTest;
import br.com.zynger.simpleab.drawer.ABTestDrawer;

/**
 * Created by Julio on 25/4/2015.
 */
public class WeightedTestDrawer implements ABTestDrawer {

    @Override
    public ABTestVariant draw(AbstractABTest test) {
        Collection variantCollection = test.getVariantsMap().values();
        ButtonTestVariant[] variants = (ButtonTestVariant[]) variantCollection.toArray(new ButtonTestVariant[variantCollection.size()]);

        double totalWeight = getTotalWeight(variants);

        int randomIndex = -1;
        double random = Math.random() * totalWeight;
        for (int i = 0; i < variants.length; ++i) {
            random -= variants[i].getWeight();
            if (random <= 0.0d) {
                randomIndex = i;
                break;
            }
        }

        return variants[randomIndex];
    }

    private double getTotalWeight(ButtonTestVariant[] variants) {
        double totalWeight = 0.0d;
        for (ButtonTestVariant variant : variants) {
            totalWeight += variant.getWeight();
        }
        return totalWeight;
    }
}
