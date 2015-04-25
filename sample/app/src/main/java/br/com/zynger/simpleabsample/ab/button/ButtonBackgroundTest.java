package br.com.zynger.simpleabsample.ab.button;

import br.com.zynger.simpleab.ABTestPerformer;
import br.com.zynger.simpleab.AbstractABTest;
import br.com.zynger.simpleab.drawer.ABTestDrawer;

/**
 * Created by Julio on 25/4/2015.
 */
public class ButtonBackgroundTest extends AbstractABTest<String> {

    private final static String TEST_ID = ButtonBackgroundTest.class.getSimpleName();

    public ButtonBackgroundTest(ABTestPerformer performer, ButtonTestVariant... variants) {
        super(TEST_ID, performer, variants);
    }

    @Override
    public void registerTest(ABTestPerformer performer) {
        performer.registerTest(this, new WeightedTestDrawer());
    }
}
