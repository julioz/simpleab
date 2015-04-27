package br.com.zynger.simpleab;

/**
 * Created by Julio on 27/4/2015.
 */
public interface ABTestListener {
    void onTestPerformed(AbstractABTest test, ABTestVariant variant);
}
