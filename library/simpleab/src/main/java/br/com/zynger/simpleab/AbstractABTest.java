package br.com.zynger.simpleab;

import java.util.HashMap;

/**
 * Created by Julio on 25/4/2015.
 */
public abstract class AbstractABTest {

    private String mIdentifier;
    private HashMap<String, ABTestVariant> mVariantsMap;

    public AbstractABTest(String identifier, ABTestVariant... variants) {
        this.mIdentifier = identifier;

        if (variants == null || variants.length < 2) {
            throw new IllegalArgumentException("You cannot define an ABTest" +
                    " with less than two variants!");
        }

        this.mVariantsMap = new HashMap<>();
        for (ABTestVariant variant : variants) {
            String variantId = variant.getId();
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
    }

    public String getId() {
        return mIdentifier;
    }

    public HashMap<String, ABTestVariant> getVariantsMap() {
        return mVariantsMap;
    }

    public ABTestVariant getVariantById(String variantId) {
        return mVariantsMap.get(variantId);
    }
}
