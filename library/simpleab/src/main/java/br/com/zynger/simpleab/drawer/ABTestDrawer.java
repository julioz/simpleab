package br.com.zynger.simpleab.drawer;


import br.com.zynger.simpleab.ABTestVariant;
import br.com.zynger.simpleab.AbstractABTest;

/**
 * Created by Julio on 25/4/2015.
 */
public interface ABTestDrawer {
    ABTestVariant draw(AbstractABTest test);
}
