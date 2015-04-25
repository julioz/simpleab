package br.com.zynger.simpleabsample.library;

import java.util.HashMap;

/**
 * Created by Julio on 25/4/2015.
 */
public abstract class AbstractABTest<I> {

    private I mIdentifier;
    private HashMap<Object, ABTestVariant> mVariantsMap;

    public AbstractABTest(I identifier, ABTestVariant... variants) {
        this.mIdentifier = identifier;

        this.mVariantsMap = new HashMap<>();
        for (int i = 0; i < variants.length; i++) {
            ABTestVariant variant = variants[i];
            this.mVariantsMap.put(variant.getId(), variant);
        }
    }

    public I getIdentifier() {
        return mIdentifier;
    }
}
