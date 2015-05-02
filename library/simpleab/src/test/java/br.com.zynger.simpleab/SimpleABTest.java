package br.com.zynger.simpleab;

import android.content.Context;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static junit.framework.Assert.assertNotNull;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(RobolectricTestRunner.class)
@Config(emulateSdk = 18, reportSdk = 18)
public class SimpleABTest {

    @Mock
    Context context;
    
    private SimpleAB mSimpleAB;

    @Before
    public void setUp() {
        initMocks(this);
        mSimpleAB = new SimpleAB(context);
    }

    @Test
    public void testInstanceNotNull() {
        assertNotNull(mSimpleAB);
    }
}