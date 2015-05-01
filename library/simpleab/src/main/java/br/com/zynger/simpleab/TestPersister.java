package br.com.zynger.simpleab;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by Julio on 1/5/2015.
 */
public class TestPersister {

    private static final String PREF_TEST_PREFIX = "simpleab_test_";

    private final SharedPreferences mSharedPreferences;

    TestPersister(Context context) {
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public void persistVariant(AbstractABTest test, ABTestVariant variant) {
        String key = getPersistenceTestKey(test);
        String variantId = variant.getId();

        mSharedPreferences.edit().putString(key, variantId).apply();
    }

    private String getPersistenceTestKey(AbstractABTest test) {
        String testId = test.getId();
        return PREF_TEST_PREFIX + testId;
    }

    public ABTestVariant getPersistedVariant(AbstractABTest test) {
        String key = getPersistenceTestKey(test);
        String variantId = mSharedPreferences.getString(key, null);

        if (variantId == null) {
            return null;
        }

        return test.getVariantById(variantId);
    }
}
