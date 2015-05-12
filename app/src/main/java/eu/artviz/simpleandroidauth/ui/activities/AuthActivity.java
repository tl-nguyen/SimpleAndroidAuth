package eu.artviz.simpleandroidauth.ui.activities;

import android.os.Bundle;

import com.squareup.otto.Subscribe;

import eu.artviz.simpleandroidauth.R;
import eu.artviz.simpleandroidauth.navigation.NavigationEvent;
import eu.artviz.simpleandroidauth.ui.fragments.LoginFragment;


public class AuthActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        onNavigationEvent(new NavigationEvent(new LoginFragment(), R.id.container));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_auth;
    }

    @Subscribe
    public void onNavigationEvent(NavigationEvent event) {
        consumeNavigationEvent(event);
    }
}
