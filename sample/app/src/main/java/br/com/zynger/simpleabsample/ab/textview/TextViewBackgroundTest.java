package br.com.zynger.simpleabsample.ab.textview;

import br.com.zynger.simpleab.ABTestVariant;
import br.com.zynger.simpleab.AbstractABTest;

/**
 * Created by Julio on 25/4/2015.
 */
public class TextViewBackgroundTest extends AbstractABTest {

    private final static String TEST_ID = TextViewBackgroundTest.class.getSimpleName();

    public TextViewBackgroundTest(ABTestVariant... variants) {
        super(TEST_ID, variants);
    }
}
