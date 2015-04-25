package br.com.zynger.simpleab;

import java.util.HashMap;
import java.util.Map;

import br.com.zynger.simpleab.drawer.ABTestDrawer;
import br.com.zynger.simpleab.drawer.DefaultTestDrawer;

/**
 * Created by Julio on 25/4/2015.
 */
public class ABTestPerformer {

    private DefaultTestDrawer mDefaultTestDrawer;
    private HashMap<AbstractABTest, ABTestVariant> mDrewVariantsMap;

    public ABTestPerformer() {
        mDrewVariantsMap = new HashMap<>();
        mDefaultTestDrawer = new DefaultTestDrawer();
    }

    public void registerTest(AbstractABTest test, ABTestDrawer drawer) {
        ABTestVariant chosenVariant = drawer.draw(test);

        mDrewVariantsMap.put(test, chosenVariant);
    }

    public void registerTest(AbstractABTest test) {
        registerTest(test, mDefaultTestDrawer);
    }

    public void perform(AbstractABTest test) {
        ABTestVariant variant = mDrewVariantsMap.get(test);

        if (variant == null) {
            throw new UnsupportedOperationException("There is no registered ABTest with class " +
                    test.getClass().getSimpleName() + ".");
        }

        variant.perform();
    }

    public HashMap<Object, Object> getDrewMap() {
        HashMap<Object, Object> map = new HashMap<>();

        for (Map.Entry<AbstractABTest, ABTestVariant> entry : mDrewVariantsMap.entrySet()) {
            map.put(entry.getKey().getIdentifier(), entry.getValue().getId());
        }

        return map;
    }
}
