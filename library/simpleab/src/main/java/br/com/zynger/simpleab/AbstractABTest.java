package br.com.zynger.simpleab;

import java.util.HashMap;

/**
 * Created by Julio on 25/4/2015.
 */
public abstract class AbstractABTest<I> {

    private I mIdentifier;
    private final ABTestPerformer mPerformer;
    private HashMap<Object, ABTestVariant> mVariantsMap;

    public AbstractABTest(I identifier, ABTestPerformer performer, ABTestVariant... variants) {
        this.mIdentifier = identifier;
        this.mPerformer = performer;

        if (variants == null || variants.length < 2) {
            throw new IllegalArgumentException("You cannot define an ABTestVariant" +
                    " with less than two variants!");
        }

        this.mVariantsMap = new HashMap<>();
        for (ABTestVariant variant : variants) {
            Object variantId = variant.getId();
            if (variantId == null) {
                throw new IllegalArgumentException("You cannot define an ABTestVariant" +
                        " with a null ID!");
            }

            if (mVariantsMap.containsKey(variantId)) {
                throw new IllegalArgumentException("You cannot define ABTestVariants" +
                        " with equal IDs!");
            }

            this.mVariantsMap.put(variant.getId(), variant);
        }

        mPerformer.registerTest(this);
    }

    public void perform() {
        mPerformer.perform(this);
    }

    public I getIdentifier() {
        return mIdentifier;
    }

    public HashMap<Object, ABTestVariant> getVariantsMap() {
        return mVariantsMap;
    }
}
