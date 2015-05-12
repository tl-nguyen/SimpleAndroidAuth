package eu.artviz.simpleandroidauth.ui.fragments;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.InjectView;
import eu.artviz.simpleandroidauth.R;
import eu.artviz.simpleandroidauth.models.Auth;
import eu.artviz.simpleandroidauth.models.User;
import eu.artviz.simpleandroidauth.navigation.NavigationEvent;
import eu.artviz.simpleandroidauth.networking.AuthService;
import eu.artviz.simpleandroidauth.networking.RestServiceCreator;
import eu.artviz.simpleandroidauth.ui.activities.MainActivity;
import eu.artviz.simpleandroidauth.utils.BusProvider;
import eu.artviz.simpleandroidauth.utils.CachedDb;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.mime.TypedByteArray;


public class LoginFragment extends BaseFragment {

    private CachedDb mCachedDb;

    @InjectView(R.id.etEmail)
    EditText mEtEmail;

    @InjectView(R.id.etPassword)
    EditText mEtPassword;

    @InjectView(R.id.tvRegister)
    TextView mTvRegister;

    @InjectView(R.id.btnLogin)
    Button mBtnLogin;

    public LoginFragment() {
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_login;
    }

    @Override
    protected void initUI() {
        mCachedDb = CachedDb.getInstance(getActivity());

        mTvRegister.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                BusProvider.getInstance().post(new NavigationEvent(new RegisterFragment(), R.id.container));
            }
        });

        mBtnLogin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                User user = new User();
                user.setEmail(mEtEmail.getText().toString());
                user.setPassword(mEtPassword.getText().toString());

                AuthService authService = RestServiceCreator.createAuthService();

                authService.login(user, new Callback<Auth>() {
                    @Override
                    public void success(Auth auth, Response response) {
                        mCachedDb.setToken(auth.getToken());
                        mCachedDb.setCurrentUser(auth.getUser());

                        Intent mainIntent = new Intent(getActivity(), MainActivity.class);
                        startActivity(mainIntent);

                        getActivity().finish();
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        String errorResponseBody = new String(((TypedByteArray) error.getResponse().getBody()).getBytes());
                        Log.d("Test", "Error: " + error.getMessage());
                        Log.d("Test", errorResponseBody);
                    }
                });
            }
        });
    }
}
