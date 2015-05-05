package br.com.zynger.simpleab;

import android.content.Context;

import br.com.zynger.simpleab.drawer.ABTestDrawer;
import br.com.zynger.simpleab.drawer.DefaultTestDrawer;

/**
 * Created by Julio on 25/4/2015.
 */
public class SimpleAB {
    static volatile SimpleAB sSingleton = null;

    private final TestPersister mTestPersister;
    private ABTestDrawer mDefaultTestDrawer;

    private static boolean sAlwaysDrawVariants = false;

    SimpleAB(ABTestDrawer defaultTestDrawer, TestPersister testPersister) {
        mDefaultTestDrawer = defaultTestDrawer;
        mTestPersister = testPersister;
    }

    public static SimpleAB with(Context context) {
        if (sSingleton == null) {
            synchronized (SimpleAB.class) {
                if (sSingleton == null) {
                    sSingleton = new Builder(context).build();
                }
            }
        }
        return sSingleton;
    }

    public TestPerformer perform(AbstractABTest test) {
        return new TestPerformer(sSingleton, test);
    }

    void executeTest(AbstractABTest test, ABTestDrawer drawer, ABTestListener listener) {
        if (drawer == null) {
            drawer = mDefaultTestDrawer;
        }

        ABTestVariant variant = mTestPersister.getPersistedVariant(test);
        if (sAlwaysDrawVariants || variant == null) {
            variant = drawer.draw(test);
            mTestPersister.persistVariant(test, variant);
        }

        variant.perform();

        if (listener != null) {
            listener.onTestPerformed(test, variant);
        }
    }

    public void alwaysDrawVariants() {
        sAlwaysDrawVariants = true;
    }

    public static class Builder {
        private final Context mContext;

        public Builder(Context context) {
            if (context == null) {
                throw new IllegalArgumentException("Context must not be null.");
            }
            this.mContext = context.getApplicationContext();
        }

        public SimpleAB build() {
            Context context = this.mContext;
            ABTestDrawer defaultDrawer = new DefaultTestDrawer();
            TestPersister persister = new TestPersister(context);
            return new SimpleAB(defaultDrawer, persister);
        }
    }
}
