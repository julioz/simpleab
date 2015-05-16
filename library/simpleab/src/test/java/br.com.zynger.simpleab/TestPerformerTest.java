package br.com.zynger.simpleab;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import br.com.zynger.simpleab.drawer.ABTestDrawer;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Matchers.isNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * Created by Julio on 16/5/2015.
 */
public class TestPerformerTest {

    @Mock
    SimpleAB mSimpleAB;

    @Mock
    AbstractABTest mTest;

    private TestPerformer mPerformer;

    @Before
    public void setUp() {
        initMocks(this);
        mPerformer = new TestPerformer(mSimpleAB, mTest);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSettingNullDrawerThrowsException() {
        mPerformer.withDrawer(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSettingNullListenerThrowsException() {
        mPerformer.listener(null);
    }

    @Test
    public void testABTestInstanceFromConstructorIsUsedInNowCall() {
        mPerformer.now();

        verify(mSimpleAB).executeTest(eq(mTest), any(ABTestDrawer.class), any(ABTestListener.class));
    }

    @Test
    public void testSettingDrawerActuallyIsSentToSimpleABInstance() {
        ABTestDrawer drawer = mock(ABTestDrawer.class);

        mPerformer.withDrawer(drawer).now();

        verify(mSimpleAB).executeTest(eq(mTest), eq(drawer), isNull(ABTestListener.class));
    }

    @Test
    public void testSettingListenerActuallyIsSentToSimpleABInstance() {
        ABTestListener listener = mock(ABTestListener.class);

        mPerformer.listener(listener).now();

        verify(mSimpleAB).executeTest(eq(mTest), isNull(ABTestDrawer.class), eq(listener));
    }

    @Test
    public void testSettingDrawerAndListenerActuallyAreSentToSimpleABInstance() {
        ABTestDrawer drawer = mock(ABTestDrawer.class);
        ABTestListener listener = mock(ABTestListener.class);

        mPerformer.withDrawer(drawer).listener(listener).now();

        verify(mSimpleAB).executeTest(eq(mTest), eq(drawer), eq(listener));
    }
}
