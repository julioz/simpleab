package br.com.zynger.simpleab;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * Created by Julio on 16/5/2015.
 */
public class AbstractABTestTest {

    private class MockABTest extends AbstractABTest {

        public MockABTest(String identifier, ABTestVariant... variants) {
            super(identifier, variants);
        }
    }

    String mMockIdentifier = "mockTestId";

    @Mock
    ABTestVariant mMockTestVariantA;

    @Mock
    ABTestVariant mMockTestVariantB;

    private MockABTest mTest;

    @Before
    public void setUp() {
        initMocks(this);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructingWithNullVariantsThrowException() {
        ABTestVariant[] variants = null;
        mTest = new MockABTest(mMockIdentifier, variants);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructingWithNoVariantsThrowException() {
        mTest = new MockABTest(mMockIdentifier);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructingWithOneVariantThrowException() {
        mTest = new MockABTest(mMockIdentifier, mMockTestVariantA);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPassingVariantWithNullIdThrowsException() {
        when(mMockTestVariantA.getId()).thenReturn(null);
        when(mMockTestVariantB.getId()).thenReturn(null);

        mTest = new MockABTest(mMockIdentifier, mMockTestVariantA, mMockTestVariantB);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPassingVariantsWithSameIdThrowsException() {
        String commonId = "mockId";

        when(mMockTestVariantA.getId()).thenReturn(commonId);
        when(mMockTestVariantB.getId()).thenReturn(commonId);

        mTest = new MockABTest(mMockIdentifier, mMockTestVariantA, mMockTestVariantB);
    }

    @Test
    public void testGetIdReturnsTheIdOfTheTest() {
        when(mMockTestVariantA.getId()).thenReturn("A");
        when(mMockTestVariantB.getId()).thenReturn("B");

        mTest = new MockABTest(mMockIdentifier, mMockTestVariantA, mMockTestVariantB);

        assertEquals(mMockIdentifier, mTest.getId());
    }

    @Test
    public void testVariantMapBuildingContainsTheActualPassedVariants() {
        String variantAKey = "A";
        String variantBKey = "B";

        when(mMockTestVariantA.getId()).thenReturn(variantAKey);
        when(mMockTestVariantB.getId()).thenReturn(variantBKey);

        mTest = new MockABTest(mMockIdentifier, mMockTestVariantA, mMockTestVariantB);

        assertNotNull(mTest.getVariantsMap());
        assertEquals(2, mTest.getVariantsMap().size());
        assertEquals(mMockTestVariantA, mTest.getVariantsMap().get(variantAKey));
        assertEquals(mMockTestVariantB, mTest.getVariantsMap().get(variantBKey));
    }

    @Test
    public void testGetVariantByIdReturnsTheActualPassedVariants() {
        String variantAKey = "A";
        String variantBKey = "B";

        when(mMockTestVariantA.getId()).thenReturn(variantAKey);
        when(mMockTestVariantB.getId()).thenReturn(variantBKey);

        mTest = new MockABTest(mMockIdentifier, mMockTestVariantA, mMockTestVariantB);

        assertNotNull(mTest.getVariantsMap());
        assertEquals(mMockTestVariantA, mTest.getVariantsMap().get(variantAKey));
        assertEquals(mMockTestVariantB, mTest.getVariantsMap().get(variantBKey));
    }
}
