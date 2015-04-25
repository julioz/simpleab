package br.com.zynger.simpleabsample;

import br.com.zynger.simpleab.ABTestPerformer;
import br.com.zynger.simpleab.ABTestVariant;
import br.com.zynger.simpleab.AbstractABTest;

/**
 * Created by Julio on 25/4/2015.
 */
public class TextViewBackgroundTest extends AbstractABTest<String> {

    private final static String TEST_ID = TextViewBackgroundTest.class.getSimpleName();

    public TextViewBackgroundTest(ABTestPerformer performer, ABTestVariant... variants) {
        super(TEST_ID, performer, variants);
    }
}
