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
    private DefaultTestDrawer mDefaultTestDrawer;

    private static boolean sAlwaysDrawVariants = false;

    SimpleAB(Context context) {
        mDefaultTestDrawer = new DefaultTestDrawer();
        mTestPersister = new TestPersister(context);
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

            return new SimpleAB(context);
        }
    }
}
