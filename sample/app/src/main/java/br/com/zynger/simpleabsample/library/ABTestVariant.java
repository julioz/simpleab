package br.com.zynger.simpleabsample.library;

/**
 * Created by Julio on 25/4/2015.
 */
public class ABTestVariant<I> {

    private I mId;

    public ABTestVariant(I id) {
        mId = id;
    }

    public I getId() {
        return mId;
    }
}
