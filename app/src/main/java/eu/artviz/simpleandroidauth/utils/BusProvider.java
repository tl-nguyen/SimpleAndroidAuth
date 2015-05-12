package eu.artviz.simpleandroidauth.utils;

import com.squareup.otto.Bus;

/**
 * Created by tung.lam.nguyen on 12.5.2015 ã..
 */
public final class BusProvider {
    private static final Bus BUS = new Bus();

    public static Bus getInstance(){
        return BUS;
    }

    private BusProvider(){}
}
