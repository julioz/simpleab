package br.com.zynger.simpleab.drawer;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.HashMap;

import br.com.zynger.simpleab.ABTestVariant;
import br.com.zynger.simpleab.AbstractABTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * Created by Julio on 16/5/2015.
 */
public class DefaultTestDrawerTest {

    private DefaultTestDrawer mDefaultTestDrawer;

    @Mock
    AbstractABTest mMockTest;

    @Before
    public void setUp() {
        initMocks(this);
        mDefaultTestDrawer = new DefaultTestDrawer();
    }

    @Test
    public void testDrawReturnsOneOfTheTestsVariants() {
        HashMap<String, ABTestVariant> variantMap = new HashMap();
        ABTestVariant mockVariantA = mock(ABTestVariant.class);
        ABTestVariant mockVariantB = mock(ABTestVariant.class);
        ABTestVariant mockVariantC = mock(ABTestVariant.class);

        variantMap.put("A", mockVariantA);
        variantMap.put("B", mockVariantB);
        variantMap.put("C", mockVariantC);
        when(mMockTest.getVariantsMap()).thenReturn(variantMap);

        ABTestVariant variant = mDefaultTestDrawer.draw(mMockTest);

        assertTrue(variant == mockVariantA || variant == mockVariantB || variant == mockVariantC);
    }
}
