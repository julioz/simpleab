package br.com.zynger.simpleabsample.ab.button;

import br.com.zynger.simpleab.AbstractABTest;

/**
 * Created by Julio on 25/4/2015.
 */
public class ButtonBackgroundTest extends AbstractABTest<String> {

    private final static String TEST_ID = ButtonBackgroundTest.class.getSimpleName();

    public ButtonBackgroundTest(ButtonTestVariant... variants) {
        super(TEST_ID, variants);
    }
}
