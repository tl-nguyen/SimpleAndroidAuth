package eu.artviz.simpleandroidauth.ui.activities;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import eu.artviz.simpleandroidauth.ui.fragments.LoginFragment;
import eu.artviz.simpleandroidauth.R;
import eu.artviz.simpleandroidauth.ui.fragments.RegisterFragment;


public class AuthActivity extends ActionBarActivity implements LoginFragment.OnRegisterSelectedListener, RegisterFragment.OnCancelSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.container, new LoginFragment())
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onRegisterSelected() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, new RegisterFragment())
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onCancelSelected() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, new LoginFragment())
                .addToBackStack(null)
                .commit();
    }
}
