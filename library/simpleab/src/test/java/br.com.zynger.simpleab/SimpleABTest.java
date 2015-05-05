package br.com.zynger.simpleab;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import br.com.zynger.simpleab.drawer.ABTestDrawer;
import br.com.zynger.simpleab.drawer.DefaultTestDrawer;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNull;
import static junit.framework.Assert.fail;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(RobolectricTestRunner.class)
@Config(emulateSdk = 18, reportSdk = 18)
public class SimpleABTest {

    @Mock
    DefaultTestDrawer mMockDefaultTestDrawer;

    @Mock
    TestPersister mTestPersister;

    @Mock
    ABTestVariant mMockPersistedTestVariant;

    @Mock
    AbstractABTest mMockTest;

    private SimpleAB mSimpleAB;

    @Before
    public void setUp() {
        initMocks(this);
        mSimpleAB = new SimpleAB(mMockDefaultTestDrawer, mTestPersister);
    }

    @Test
    public void testInstanceNotNull() {
        assertNotNull(mSimpleAB);
    }

    @Test
    public void builderInvalidContext() throws Exception {
        try {
            new SimpleAB.Builder(null);
            fail("Null context should throw exception.");
        } catch (IllegalArgumentException expected) {
        }
    }

    @Test
    public void testPerformerIsBuilt() {
        TestPerformer performer = mSimpleAB.perform(mMockTest);
        assertNotNull(performer);
    }

    @Test
    public void executionWithNullDrawerUsesDefaultOne() {
        when(mTestPersister.getPersistedVariant(mMockTest)).thenReturn(null);
        when(mMockDefaultTestDrawer.draw(mMockTest)).thenReturn(mMockPersistedTestVariant);

        mSimpleAB.executeTest(mMockTest, null, null);

        verify(mMockDefaultTestDrawer).draw(mMockTest);
    }

    @Test
    public void executionWithCustomDrawerUsesCustomOne() {
        ABTestDrawer customDrawer = mock(ABTestDrawer.class);
        when(customDrawer.draw(mMockTest)).thenReturn(mMockPersistedTestVariant);
        when(mTestPersister.getPersistedVariant(mMockTest)).thenReturn(null);

        mSimpleAB.executeTest(mMockTest, customDrawer, null);

        verify(mMockDefaultTestDrawer, never()).draw(any(AbstractABTest.class));
        verify(customDrawer).draw(mMockTest);
    }

    @Test
    public void executionWithNoPersistedVariantPersistsOne() {
        when(mTestPersister.getPersistedVariant(mMockTest)).thenReturn(null);
        when(mMockDefaultTestDrawer.draw(mMockTest)).thenReturn(mMockPersistedTestVariant);

        mSimpleAB.executeTest(mMockTest, null, null);

        verify(mTestPersister).persistVariant(eq(mMockTest), any(ABTestVariant.class));
    }

    @Test
    public void executionWithPersistedVariantDoesNotDrawOne() {
        when(mTestPersister.getPersistedVariant(mMockTest)).thenReturn(mMockPersistedTestVariant);

        mSimpleAB.executeTest(mMockTest, null, null);

        verify(mMockDefaultTestDrawer, never()).draw(any(AbstractABTest.class));
    }

    @Test
    public void executionWithPersistedVariantDoesNotPersistsOne() {
        when(mTestPersister.getPersistedVariant(mMockTest)).thenReturn(mMockPersistedTestVariant);

        mSimpleAB.executeTest(mMockTest, null, null);

        verify(mTestPersister, never()).persistVariant(eq(mMockTest), any(ABTestVariant.class));
    }

    @Test
    public void executionWithoutPersistedVariantWillExecuteTheDrawnOne() {
        when(mTestPersister.getPersistedVariant(mMockTest)).thenReturn(null);
        when(mMockDefaultTestDrawer.draw(mMockTest)).thenReturn(mMockPersistedTestVariant);

        mSimpleAB.executeTest(mMockTest, null, null);

        verify(mMockPersistedTestVariant).perform();
    }

    @Test
    public void executionWithPersistedVariantWillExecuteThatOne() {
        when(mTestPersister.getPersistedVariant(mMockTest)).thenReturn(mMockPersistedTestVariant);

        mSimpleAB.executeTest(mMockTest, null, null);

        verify(mMockPersistedTestVariant).perform();
    }

    @Test
    public void executionWithoutListenerWillNeverCallIt() {
        when(mMockDefaultTestDrawer.draw(mMockTest)).thenReturn(mMockPersistedTestVariant);
        ABTestListener listener = null;

        mSimpleAB.executeTest(mMockTest, null, listener);

        assertNull(listener);
    }

    @Test
    public void executionWithListenerWillCallIt() {
        ABTestListener listener = mock(ABTestListener.class);
        when(mTestPersister.getPersistedVariant(mMockTest)).thenReturn(mMockPersistedTestVariant);

        mSimpleAB.executeTest(mMockTest, null, listener);

        assertNotNull(listener);
        verify(listener).onTestPerformed(mMockTest, mMockPersistedTestVariant);
    }
}