package br.com.zynger.simpleab;

import br.com.zynger.simpleab.drawer.ABTestDrawer;

/**
 * Created by Julio on 27/4/2015.
 */
public class TestPerformer {

    private final SimpleAB mSimpleAB;
    private final AbstractABTest mTest;
    private ABTestDrawer mABTestDrawer;
    private ABTestListener mTestListener;

    TestPerformer(SimpleAB simpleAB, AbstractABTest test) {
        this.mSimpleAB = simpleAB;
        this.mTest = test;
    }

    public TestPerformer withDrawer(ABTestDrawer drawer) {
        if (drawer == null) {
            throw new IllegalArgumentException("ABTestDrawer must not be null.");
        }

        this.mABTestDrawer = drawer;
        return this;
    }

    public TestPerformer listener(ABTestListener listener) {
        if (listener == null) {
            throw new IllegalArgumentException("ABTestListener must not be null.");
        }

        this.mTestListener = listener;
        return this;
    }

    public void now() {
        mSimpleAB.executeTest(mTest, mABTestDrawer, mTestListener);
    }
}
