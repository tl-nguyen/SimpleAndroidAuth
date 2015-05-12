package eu.artviz.simpleandroidauth.ui.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;

import eu.artviz.simpleandroidauth.navigation.NavigationEvent;
import eu.artviz.simpleandroidauth.utils.BusProvider;


public abstract class BaseActivity extends ActionBarActivity {

    @Override
    protected void onStart() {
        super.onStart();
        BusProvider.getInstance().register(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
    }

    @Override
    protected void onStop() {
        BusProvider.getInstance().unregister(this);
        super.onPause();
    }

    protected abstract int getLayoutId();

    protected final void consumeNavigationEvent(NavigationEvent event) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        int containerId;
        if(event.getContainerId() != 0) {
            containerId = event.getContainerId();

            transaction.replace(containerId, event.getFragment());
            transaction.commit();
        }
    }
}
