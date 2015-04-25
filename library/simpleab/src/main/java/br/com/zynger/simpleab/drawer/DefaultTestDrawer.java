package br.com.zynger.simpleab.drawer;

import java.util.Random;

import br.com.zynger.simpleab.ABTestVariant;
import br.com.zynger.simpleab.AbstractABTest;

/**
 * Created by Julio on 25/4/2015.
 */
public class DefaultTestDrawer implements ABTestDrawer {

    private Random mRandomGenerator;

    public DefaultTestDrawer() {
        mRandomGenerator = new Random();
    }

    @Override
    public ABTestVariant draw(AbstractABTest test) {
        Object[] variants = test.getVariantsMap().values().toArray();
        int index = mRandomGenerator.nextInt(variants.length);

        return (ABTestVariant) variants[index];
    }
}
