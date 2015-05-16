package br.com.zynger.simpleab;

import android.content.Context;
import android.content.SharedPreferences;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.internal.matchers.Null;

import static org.junit.Assert.assertNull;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.contains;
import static org.mockito.Matchers.eq;
import static org.mockito.Matchers.isNull;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * Created by Julio on 16/5/2015.
 */
public class TestPersisterTest {

    @Mock
    SharedPreferences mMockSharedPreferences;

    @Mock
    SharedPreferences.Editor mMockEditor;

    @Mock
    AbstractABTest mMockTest;

    @Mock
    ABTestVariant mMockVariant;

    private TestPersister mPersister;

    @Before
    public void setUp() {
        initMocks(this);
        mPersister = new TestPersister(mMockSharedPreferences);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructingWithNullSharedPreferencesThrowsException() {
        SharedPreferences preferences = null;
        new TestPersister(preferences);
    }

    @Test
    public void testPersistVariantCallsSharedPreferencesInstance() {
        String mockVariantId = "mockVarId";
        String mockTestId = "mockTestId";

        when(mMockVariant.getId()).thenReturn(mockVariantId);
        when(mMockTest.getId()).thenReturn(mockTestId);
        when(mMockSharedPreferences.edit()).thenReturn(mMockEditor);
        when(mMockEditor.putString(anyString(), anyString())).thenReturn(mMockEditor);

        mPersister.persistVariant(mMockTest, mMockVariant);

        verify(mMockEditor).putString(contains(mockTestId), eq(mockVariantId));
        verify(mMockEditor).apply();
    }

    @Test
    public void testGettingThePersistedVariantWhenThereIsOneReturnsTheActualOne() {
        String mockVariantId = "mockId";
        when(mMockSharedPreferences.getString(anyString(), anyString())).thenReturn(mockVariantId);

        mPersister.getPersistedVariant(mMockTest);

        verify(mMockTest).getVariantById(mockVariantId);
    }

    @Test
    public void testGettingThePersistedVariantWhenThereIsNoneReturnsNull() {
        when(mMockSharedPreferences.getString(anyString(), anyString())).thenReturn(null);

        ABTestVariant variant = mPersister.getPersistedVariant(mMockTest);

        assertNull(variant);
        verify(mMockTest, never()).getVariantById(anyString());
    }
}
